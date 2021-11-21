package io.platformbuilders.cadastroclientes.unit.cliente;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import io.platformbuilders.cadastroclientes.api.v1.model.ClienteDTO;
import io.platformbuilders.cadastroclientes.api.v1.model.CreateClienteDTO;
import io.platformbuilders.cadastroclientes.api.v1.model.UpdateClienteDTO;
import io.platformbuilders.cadastroclientes.core.BaseUnitTest;
import io.platformbuilders.cadastroclientes.domain.exceptions.BusinessException;
import io.platformbuilders.cadastroclientes.domain.exceptions.ClienteNotFoundException;
import io.platformbuilders.cadastroclientes.domain.models.cliente.Cliente;
import io.platformbuilders.cadastroclientes.domain.repositories.ClienteRepository;
import io.platformbuilders.cadastroclientes.domain.services.impl.ClienteServiceImpl;
import io.platformbuilders.cadastroclientes.utils.MapperUtils;
import io.platformbuilders.cadastroclientes.utils.TestConstants;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;


class ClienteServiceUnitTest extends BaseUnitTest {

    private ClienteServiceImpl victim;

    @Mock
    private ClienteRepository clienteRepository;

    @BeforeEach
    private void setup() {
        victim = new ClienteServiceImpl(clienteRepository, MapperUtils.clienteMapper());
    }

    @Test
    void getClienteFindBySucess() {
        Cliente cliente = Cliente.builder()
            .id(TestConstants.DEFAULT_CLIENTE_ID)
            .nome(TestConstants.DEFAULT_CLIENTE_NOME)
            .rg(TestConstants.DEFAULT_CLIENTE_RG)
            .cpf(TestConstants.DEFAULT_CLIENTE_CPF)
            .dataNascimento(TestConstants.DEFAULT_CLIENTE_DATA_NASCIMENTO)
            .telefone(TestConstants.DEFAULT_CLIENTE_DATA_TELEFONE)
            .build();
        Mockito.when(clienteRepository.findById(TestConstants.DEFAULT_CLIENTE_ID)).thenReturn(Optional.of(cliente));

        ClienteDTO clienteDTO = victim.findById(TestConstants.DEFAULT_CLIENTE_ID);

        Assertions.assertEquals(cliente.getId(), clienteDTO.getId());
        Assertions.assertEquals(cliente.getNome(), clienteDTO.getNome());
        Assertions.assertEquals(cliente.getRg(), clienteDTO.getRg());
        Assertions.assertEquals(cliente.getCpf(), clienteDTO.getCpf());
        Assertions.assertEquals(cliente.getDataNascimento(), clienteDTO.getDataNascimento());
        Assertions.assertEquals(cliente.getTelefone(), clienteDTO.getTelefone());
    }

    @Test
    void getClienteNotFoundFindyBy() {
        Mockito.when(clienteRepository.findById(TestConstants.DEFAULT_CLIENTE_ID)).thenReturn(Optional.empty());
        Assertions.assertThrows(ClienteNotFoundException.class, () -> victim.findById(TestConstants.DEFAULT_CLIENTE_ID));
    }

    @Test
    void testCreateClienteSucess() {
        CreateClienteDTO createClienteDTO = CreateClienteDTO.builder()
            .nome(TestConstants.DEFAULT_CLIENTE_NOME)
            .rg(TestConstants.DEFAULT_CLIENTE_RG)
            .cpf(TestConstants.DEFAULT_CLIENTE_CPF)
            .dataNascimento(TestConstants.DEFAULT_CLIENTE_DATA_NASCIMENTO)
            .telefone(TestConstants.DEFAULT_CLIENTE_DATA_TELEFONE)
            .build();

        ClienteDTO clienteDTO = victim.create(createClienteDTO);

        Assertions.assertEquals(createClienteDTO.getNome(), clienteDTO.getNome());
        Assertions.assertEquals(createClienteDTO.getRg(), clienteDTO.getRg());
        Assertions.assertEquals(createClienteDTO.getCpf(), clienteDTO.getCpf());
        Assertions.assertEquals(createClienteDTO.getDataNascimento(), clienteDTO.getDataNascimento());
        Assertions.assertEquals(createClienteDTO.getTelefone(), clienteDTO.getTelefone());

        Mockito.verify(clienteRepository).save(any());
    }

    @Test
    void testValidateWhenCreateClienteDtoIsDuplicate() {
        Cliente cliente = Cliente.builder()
            .id(TestConstants.DEFAULT_CLIENTE_ID)
            .nome(TestConstants.DEFAULT_CLIENTE_NOME)
            .rg(TestConstants.DEFAULT_CLIENTE_RG)
            .cpf(TestConstants.DEFAULT_CLIENTE_CPF)
            .dataNascimento(TestConstants.DEFAULT_CLIENTE_DATA_NASCIMENTO)
            .telefone(TestConstants.DEFAULT_CLIENTE_DATA_TELEFONE)
            .build();

        given(clienteRepository.findByNome(TestConstants.DEFAULT_CLIENTE_NOME))
            .willReturn(Optional.of(cliente));

        CreateClienteDTO createClienteDTO = CreateClienteDTO.builder()
            .nome(TestConstants.DEFAULT_CLIENTE_NOME)
            .rg(TestConstants.DEFAULT_CLIENTE_RG)
            .cpf(TestConstants.DEFAULT_CLIENTE_CPF)
            .dataNascimento(TestConstants.DEFAULT_CLIENTE_DATA_NASCIMENTO)
            .telefone(TestConstants.DEFAULT_CLIENTE_DATA_TELEFONE)
            .build();

        Assertions.assertThrows(BusinessException.class, () -> victim.create(createClienteDTO));
    }

    @Test
    void testValidateWhenUpdateClienteDtoIsDuplicate() {

        Cliente clienteJaExistente = Cliente.builder()
            .id(TestConstants.DEFAULT_CLIENTE_ID_EXIST)
            .nome(TestConstants.DEFAULT_CLIENTE_NOME_EXIST)
            .rg(TestConstants.DEFAULT_CLIENTE_RG)
            .cpf(TestConstants.DEFAULT_CLIENTE_CPF)
            .dataNascimento(TestConstants.DEFAULT_CLIENTE_DATA_NASCIMENTO)
            .telefone(TestConstants.DEFAULT_CLIENTE_DATA_TELEFONE)
            .build();

        Cliente cliente = Cliente.builder()
            .id(TestConstants.DEFAULT_CLIENTE_ID)
            .nome(TestConstants.DEFAULT_CLIENTE_NOME)
            .rg(TestConstants.DEFAULT_CLIENTE_RG)
            .cpf(TestConstants.DEFAULT_CLIENTE_CPF)
            .dataNascimento(TestConstants.DEFAULT_CLIENTE_DATA_NASCIMENTO)
            .telefone(TestConstants.DEFAULT_CLIENTE_DATA_TELEFONE)
            .build();

        given(clienteRepository.findByNome(TestConstants.DEFAULT_CLIENTE_NOME_EXIST))
            .willReturn(Optional.of(clienteJaExistente));

        UpdateClienteDTO updateClienteDTO = UpdateClienteDTO.builder()
            .nome(TestConstants.DEFAULT_CLIENTE_NOME_EXIST)
            .rg(TestConstants.DEFAULT_CLIENTE_RG)
            .cpf(TestConstants.DEFAULT_CLIENTE_CPF)
            .dataNascimento(TestConstants.DEFAULT_CLIENTE_DATA_NASCIMENTO)
            .telefone(TestConstants.DEFAULT_CLIENTE_DATA_TELEFONE)
            .build();

        Mockito.when(clienteRepository.findById(TestConstants.DEFAULT_CLIENTE_ID)).thenReturn(Optional.of(cliente));

        Assertions.assertThrows(BusinessException.class, () -> victim.update(TestConstants.DEFAULT_CLIENTE_ID, updateClienteDTO));
    }

}
