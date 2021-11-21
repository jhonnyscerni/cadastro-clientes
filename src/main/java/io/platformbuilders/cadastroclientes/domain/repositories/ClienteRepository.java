package io.platformbuilders.cadastroclientes.domain.repositories;

import io.platformbuilders.cadastroclientes.domain.models.cliente.Cliente;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClienteRepository extends JpaRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente> {

    Optional<Cliente> findByNome(String nome);

}
