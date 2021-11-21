package io.platformbuilders.cadastroclientes.utils;

import java.time.LocalDate;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TestConstants {

    public static final long DEFAULT_CLIENTE_ID = 1L;
    public static final String DEFAULT_CLIENTE_NOME = "Fulano de Tal";
    public static final long DEFAULT_CLIENTE_ID_EXIST = 2L;
    public static final String DEFAULT_CLIENTE_NOME_EXIST = "Existente";
    public static final String DEFAULT_CLIENTE_RG = "199870664";
    public static final String DEFAULT_CLIENTE_CPF = "03115020082";
    public static final LocalDate DEFAULT_CLIENTE_DATA_NASCIMENTO = LocalDate.now();
    public static final String DEFAULT_CLIENTE_DATA_TELEFONE = "03115020082";
}
