package io.platformbuilders.cadastroclientes.utils;

import io.platformbuilders.cadastroclientes.domain.models.cliente.mapper.ClienteMapper;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;

@AllArgsConstructor
public final class MapperUtils {

    public static ClienteMapper clienteMapper() {
        return Mappers.getMapper(ClienteMapper.class);
    }

}