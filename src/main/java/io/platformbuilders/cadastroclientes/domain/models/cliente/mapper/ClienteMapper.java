package io.platformbuilders.cadastroclientes.domain.models.cliente.mapper;

import io.platformbuilders.cadastroclientes.api.v1.model.ClienteDTO;
import io.platformbuilders.cadastroclientes.api.v1.model.CreateClienteDTO;
import io.platformbuilders.cadastroclientes.api.v1.model.UpdateClienteDTO;
import io.platformbuilders.cadastroclientes.domain.models.cliente.Cliente;
import io.platformbuilders.cadastroclientes.utils.ModelMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClienteMapper extends ModelMapper<Cliente, ClienteDTO> {

    @Mapping(target = "idade", expression = "java(entity.getIdade())")
    ClienteDTO toModel(Cliente entity);

    UpdateClienteDTO toModelUpdate(Cliente entity);

    Cliente create(CreateClienteDTO createClienteDTO);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Cliente entity, UpdateClienteDTO model);

}
