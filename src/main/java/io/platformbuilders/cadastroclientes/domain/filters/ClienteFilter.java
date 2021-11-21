package io.platformbuilders.cadastroclientes.domain.filters;

import java.time.LocalDate;
import lombok.Data;

@Data
public class ClienteFilter {

    private String nome;

    private String rg;

    private String cpf;

    private LocalDate dataInicio;

    private LocalDate dataFim;
}
