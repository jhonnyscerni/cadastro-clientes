package io.platformbuilders.cadastroclientes.infrastruct.repositories.spec;

import io.platformbuilders.cadastroclientes.domain.filters.ClienteFilter;
import io.platformbuilders.cadastroclientes.domain.models.cliente.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class ClienteSpecification implements Specification<Cliente> {

    private ClienteFilter allocationFilter;

    @Override
    public Predicate toPredicate(Root<Cliente> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        Optional.ofNullable(allocationFilter.getNome())
            .ifPresent(p -> predicates.add(criteriaBuilder.like(root.get("nome"), "%" + allocationFilter.getNome() + "%")));
        Optional.ofNullable(allocationFilter.getRg())
            .ifPresent(p -> predicates.add(criteriaBuilder.equal(root.get("rg"), allocationFilter.getRg())));
        Optional.ofNullable(allocationFilter.getCpf())
            .ifPresent(p -> predicates.add(criteriaBuilder.equal(root.get("cpf"), allocationFilter.getCpf())));
        Optional.ofNullable(allocationFilter.getDataInicio())
            .ifPresent(p -> predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dataNascimento"), p)));
        Optional.ofNullable(allocationFilter.getDataFim())
            .ifPresent(p -> predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dataNascimento"), p)));
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
