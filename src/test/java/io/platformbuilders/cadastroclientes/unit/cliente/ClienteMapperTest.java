package io.platformbuilders.cadastroclientes.unit.cliente;

import io.platformbuilders.cadastroclientes.api.v1.model.ClienteDTO;
import io.platformbuilders.cadastroclientes.api.v1.model.CreateClienteDTO;
import io.platformbuilders.cadastroclientes.api.v1.model.UpdateClienteDTO;
import io.platformbuilders.cadastroclientes.domain.models.cliente.Cliente;
import io.platformbuilders.cadastroclientes.domain.models.cliente.mapper.ClienteMapper;
import io.platformbuilders.cadastroclientes.utils.MapperUtils;
import io.platformbuilders.cadastroclientes.utils.TestConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClienteMapperTest {

    private ClienteMapper victin;

    @BeforeEach
    void setup() {
        victin = MapperUtils.clienteMapper();
    }

    @Test
    void testFromEntityToClienteDto() {
        Cliente cliente = Cliente.builder()
            .id(TestConstants.DEFAULT_CLIENTE_ID)
            .nome(TestConstants.DEFAULT_CLIENTE_NOME)
            .rg(TestConstants.DEFAULT_CLIENTE_RG)
            .cpf(TestConstants.DEFAULT_CLIENTE_CPF)
            .dataNascimento(TestConstants.DEFAULT_CLIENTE_DATA_NASCIMENTO)
            .telefone(TestConstants.DEFAULT_CLIENTE_DATA_TELEFONE)
            .build();
        ClienteDTO clienteDTO = victin.toModel(cliente);

        Assertions.assertEquals(cliente.getId(), clienteDTO.getId());
        Assertions.assertEquals(cliente.getNome(), clienteDTO.getNome());
        Assertions.assertEquals(cliente.getRg(), clienteDTO.getRg());
        Assertions.assertEquals(cliente.getCpf(), clienteDTO.getCpf());
        Assertions.assertEquals(cliente.getDataNascimento(), clienteDTO.getDataNascimento());
        Assertions.assertEquals(cliente.getTelefone(), clienteDTO.getTelefone());
    }

    @Test
    void testFromClienteDtoToEntity() {
        ClienteDTO clienteDTO = ClienteDTO.builder()
            .id(TestConstants.DEFAULT_CLIENTE_ID)
            .nome(TestConstants.DEFAULT_CLIENTE_NOME)
            .rg(TestConstants.DEFAULT_CLIENTE_RG)
            .cpf(TestConstants.DEFAULT_CLIENTE_CPF)
            .dataNascimento(TestConstants.DEFAULT_CLIENTE_DATA_NASCIMENTO)
            .telefone(TestConstants.DEFAULT_CLIENTE_DATA_TELEFONE)
            .build();
        Cliente cliente = victin.toEntity(clienteDTO);

        Assertions.assertEquals(clienteDTO.getId(), cliente.getId());
        Assertions.assertEquals(clienteDTO.getNome(), cliente.getNome());
        Assertions.assertEquals(clienteDTO.getRg(), cliente.getRg());
        Assertions.assertEquals(clienteDTO.getCpf(), cliente.getCpf());
        Assertions.assertEquals(clienteDTO.getDataNascimento(), cliente.getDataNascimento());
        Assertions.assertEquals(clienteDTO.getTelefone(), cliente.getTelefone());
    }

    @Test
    void testFromCreateClienteDtoToEntity() {
        CreateClienteDTO createClienteDTO = CreateClienteDTO.builder()
            .nome(TestConstants.DEFAULT_CLIENTE_NOME)
            .rg(TestConstants.DEFAULT_CLIENTE_RG)
            .cpf(TestConstants.DEFAULT_CLIENTE_CPF)
            .dataNascimento(TestConstants.DEFAULT_CLIENTE_DATA_NASCIMENTO)
            .telefone(TestConstants.DEFAULT_CLIENTE_DATA_TELEFONE)
            .build();

        Cliente cliente = victin.create(createClienteDTO);

        Assertions.assertEquals(createClienteDTO.getNome(), cliente.getNome());
        Assertions.assertEquals(createClienteDTO.getRg(), cliente.getRg());
        Assertions.assertEquals(createClienteDTO.getCpf(), cliente.getCpf());
        Assertions.assertEquals(createClienteDTO.getDataNascimento(), cliente.getDataNascimento());
        Assertions.assertEquals(createClienteDTO.getTelefone(), cliente.getTelefone());
    }

    @Test
    void testFromClienteToUpdateClienteDTO() {

        Cliente cliente = Cliente.builder()
            .nome(TestConstants.DEFAULT_CLIENTE_NOME)
            .rg(TestConstants.DEFAULT_CLIENTE_RG)
            .cpf(TestConstants.DEFAULT_CLIENTE_CPF)
            .dataNascimento(TestConstants.DEFAULT_CLIENTE_DATA_NASCIMENTO)
            .telefone(TestConstants.DEFAULT_CLIENTE_DATA_TELEFONE)
            .build();

        UpdateClienteDTO updateClienteDTO =
            UpdateClienteDTO.builder()
                .nome(TestConstants.DEFAULT_CLIENTE_NOME)
                .rg(TestConstants.DEFAULT_CLIENTE_RG)
                .cpf(TestConstants.DEFAULT_CLIENTE_CPF)
                .dataNascimento(TestConstants.DEFAULT_CLIENTE_DATA_NASCIMENTO)
                .telefone(TestConstants.DEFAULT_CLIENTE_DATA_TELEFONE)
                .build();


        victin.toModelUpdate(cliente);

        Assertions.assertEquals(cliente.getNome(), updateClienteDTO.getNome());
        Assertions.assertEquals(cliente.getRg(), updateClienteDTO.getRg());
        Assertions.assertEquals(cliente.getCpf(), updateClienteDTO.getCpf());
        Assertions.assertEquals(cliente.getDataNascimento(), updateClienteDTO.getDataNascimento());
        Assertions.assertEquals(cliente.getTelefone(), updateClienteDTO.getTelefone());
    }

    @Test
    void testUpdate() {
        UpdateClienteDTO updateClienteDTO =
            UpdateClienteDTO.builder()
                .nome("Teste Update") //Alteração
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


        victin.update(cliente, updateClienteDTO);

        Assertions.assertEquals(updateClienteDTO.getNome(), cliente.getNome());
        Assertions.assertEquals(updateClienteDTO.getRg(), cliente.getRg());
        Assertions.assertEquals(updateClienteDTO.getCpf(), cliente.getCpf());
        Assertions.assertEquals(updateClienteDTO.getDataNascimento(), cliente.getDataNascimento());
        Assertions.assertEquals(updateClienteDTO.getTelefone(), cliente.getTelefone());
    }
}
