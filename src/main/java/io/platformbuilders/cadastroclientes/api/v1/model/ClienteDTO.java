package io.platformbuilders.cadastroclientes.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Fulano de Tal")
    private String nome;

    @Schema(example = "199870664")
    private String rg;

    @Schema(example = "03115020082")
    private String cpf;

    @Schema(example = "1988-05-31")
    private LocalDate dataNascimento;

    @Schema(example = "91981551702")
    private String telefone;

    @Schema(example = "33")
    private Integer idade;

}
