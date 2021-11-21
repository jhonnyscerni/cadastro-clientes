package io.platformbuilders.cadastroclientes.api.v1.controller;

import io.platformbuilders.cadastroclientes.api.v1.model.ClienteDTO;
import io.platformbuilders.cadastroclientes.api.v1.model.CreateClienteDTO;
import io.platformbuilders.cadastroclientes.api.v1.model.UpdateClienteDTO;
import io.platformbuilders.cadastroclientes.api.v1.openapi.ClienteOpenApi;
import io.platformbuilders.cadastroclientes.domain.filters.ClienteFilter;
import io.platformbuilders.cadastroclientes.domain.services.ClienteService;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController implements ClienteOpenApi {

    private final ClienteService clienteService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Page<ClienteDTO> search(ClienteFilter filter,
        @PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable pageable) {
        return clienteService.search(filter, pageable);
    }

    @Override
    @GetMapping(value = "/list", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<ClienteDTO> list() {
        return clienteService.findAll();
    }

    @Override
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ClienteDTO searchByClienteId(@PathVariable Long id) {
        return clienteService.findById(id);
    }

    @Override
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO create(@RequestBody @Valid CreateClienteDTO createClienteDTO) {
        return clienteService.create(createClienteDTO);
    }

    @Override
    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ClienteDTO update(@PathVariable Long id, @RequestBody @Valid UpdateClienteDTO updateClienteDTO) {
        return clienteService.update(id, updateClienteDTO);
    }

    @Override
    @PatchMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ClienteDTO updatePartial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
        return clienteService.updatePartial(id, campos);
    }

}
