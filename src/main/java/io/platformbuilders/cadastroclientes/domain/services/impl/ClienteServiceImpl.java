package io.platformbuilders.cadastroclientes.domain.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.platformbuilders.cadastroclientes.api.v1.model.ClienteDTO;
import io.platformbuilders.cadastroclientes.api.v1.model.CreateClienteDTO;
import io.platformbuilders.cadastroclientes.api.v1.model.UpdateClienteDTO;
import io.platformbuilders.cadastroclientes.domain.exceptions.BusinessException;
import io.platformbuilders.cadastroclientes.domain.exceptions.ClienteNotFoundException;
import io.platformbuilders.cadastroclientes.domain.filters.ClienteFilter;
import io.platformbuilders.cadastroclientes.domain.models.cliente.Cliente;
import io.platformbuilders.cadastroclientes.domain.models.cliente.mapper.ClienteMapper;
import io.platformbuilders.cadastroclientes.domain.repositories.ClienteRepository;
import io.platformbuilders.cadastroclientes.domain.services.ClienteService;
import io.platformbuilders.cadastroclientes.infrastruct.repositories.spec.ClienteSpecification;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

@AllArgsConstructor
@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    public List<ClienteDTO> findAll() {
        return clienteRepository.findAll().stream().map(clienteMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public ClienteDTO findById(Long id) {
        Objects.requireNonNull(id);
        return clienteRepository.findById(id).map(clienteMapper::toModel).orElseThrow(() -> new ClienteNotFoundException(id));
    }

    @Override
    public ClienteDTO create(CreateClienteDTO createClienteDTO) {
        existsByNome(new Cliente(), createClienteDTO.getNome());

        Cliente cliente = clienteMapper.create(createClienteDTO);
        clienteRepository.save(cliente);
        return clienteMapper.toModel(cliente);
    }

    @Override
    public ClienteDTO update(Long id, UpdateClienteDTO updateClienteDTO) {
        Cliente cliente = getCliente(id);

        existsByNome(cliente, updateClienteDTO.getNome());

        clienteMapper.update(cliente, updateClienteDTO);
        return clienteMapper.toModel(clienteRepository.save(cliente));
    }

    @Override
    public ClienteDTO updatePartial(Long id, Map<String, Object> campos) {
        Cliente cliente = getCliente(id);
        mergePatch(campos, cliente);

        UpdateClienteDTO updateClienteDTO = clienteMapper.toModelUpdate(cliente);
        existsByNome(cliente, updateClienteDTO.getNome());

        clienteMapper.update(cliente, updateClienteDTO);
        return clienteMapper.toModel(clienteRepository.save(cliente));
    }

    @Override
    public Page<ClienteDTO> search(ClienteFilter filter, Pageable pageable) {
        return clienteRepository.findAll(new ClienteSpecification(filter), pageable).map(clienteMapper::toModel);
    }

    @Override
    public void existsByNome(Cliente cliente, String nome) {
        Optional<Cliente> clienteExistente = clienteRepository.findByNome(nome);

        if (clienteExistente.isPresent() && !clienteExistente.get().equals(cliente)) {
            throw new BusinessException(
                String.format("Já existe um cliente com o nome %s ", nome));
        }
    }

    @Override
    public Cliente getCliente(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));
    }

    @Override
    public void mergePatch(Map<String, Object> dadosOrigem, Cliente cliente) {
        ObjectMapper objectMapper = new ObjectMapper();
        Cliente clienteOrigem = objectMapper.convertValue(dadosOrigem, Cliente.class);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Cliente.class, nomePropriedade);
            field.setAccessible(true);
            Object novoValor = ReflectionUtils.getField(field, clienteOrigem);

            ReflectionUtils.setField(field, cliente, novoValor);
        });
    }
}
