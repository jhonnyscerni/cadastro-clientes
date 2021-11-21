package io.platformbuilders.cadastroclientes.domain.services;

import io.platformbuilders.cadastroclientes.api.v1.model.ClienteDTO;
import io.platformbuilders.cadastroclientes.api.v1.model.CreateClienteDTO;
import io.platformbuilders.cadastroclientes.api.v1.model.UpdateClienteDTO;
import io.platformbuilders.cadastroclientes.domain.filters.ClienteFilter;
import io.platformbuilders.cadastroclientes.domain.models.cliente.Cliente;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteService {

    List<ClienteDTO> findAll();

    ClienteDTO findById(Long id);

    ClienteDTO create(CreateClienteDTO createClienteDTO);

    ClienteDTO update(Long id, UpdateClienteDTO updateClienteDTO);

    ClienteDTO updatePartial(Long id, Map<String, Object> campos);

    Page<ClienteDTO> search(ClienteFilter filter, Pageable pageable);

    Cliente getCliente(Long id);

    void existsByNome(Cliente cliente, String nome);

    void mergePatch(Map<String, Object> dadosOrigem, Cliente cliente);
}
