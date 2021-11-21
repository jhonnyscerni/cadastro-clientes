package io.platformbuilders.cadastroclientes.domain.models.cliente;

import java.time.LocalDate;
import java.time.Period;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "RG")
    private String rg;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "DT_NASCIMENTO")
    private LocalDate dataNascimento;

    @Column(name = "TELEFONE")
    private String telefone;

    @Transient
    public int getIdade() {
        if (this.getDataNascimento() != null) {
            return Period.between(this.getDataNascimento(), LocalDate.now()).getYears();
        } else {
            return 0;
        }
    }
}
