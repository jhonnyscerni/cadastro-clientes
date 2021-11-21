package io.platformbuilders.cadastroclientes.api.v1.openapi;

import io.platformbuilders.cadastroclientes.api.v1.exceptionhandler.Problem;
import io.platformbuilders.cadastroclientes.api.v1.model.ClienteDTO;
import io.platformbuilders.cadastroclientes.api.v1.model.CreateClienteDTO;
import io.platformbuilders.cadastroclientes.api.v1.model.UpdateClienteDTO;
import io.platformbuilders.cadastroclientes.domain.filters.ClienteFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Cliente", description = "Endpoint que trata dos Clientes")
public interface ClienteOpenApi {

    @Operation(description = "Pesquisar clientes", summary = "Pesquisar clientes")
    Page<ClienteDTO> search(@ParameterObject ClienteFilter filter,
        @PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) @ParameterObject Pageable pageable);

    @Operation(description = "Listar os clientes", summary = "Listar os clientes")
    List<ClienteDTO> list();

    @Operation(description = "Busca um cliente por ID", summary = "Busca um cliente por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Busca Realizada com Sucesso"),
        @ApiResponse(responseCode = "400", description = "ID do Cliente inválido", content = @Content(schema = @Schema(implementation = Problem.class))),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    ClienteDTO searchByClienteId(@PathVariable Long id);

    @Operation(description = "Cadastra um cliente", summary = "Cadastra um cliente")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Cliente cadastrado"),
        @ApiResponse(responseCode = "400", description = "Validação de Dados", content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    ClienteDTO create(@RequestBody @Valid CreateClienteDTO createClienteDTO);

    @Operation(description = "Atualiza um cliente por ID", summary = "Atualiza um cliente por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cliente atualizado"),
        @ApiResponse(responseCode = "400", description = "Validação de Dados", content = @Content(schema = @Schema(implementation = Problem.class))),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    ClienteDTO update(@PathVariable Long id, @RequestBody @Valid UpdateClienteDTO updateClienteDTO);

    @Operation(description = "Atualiza um cliente por ID de Forma Parcial", summary = "Atualiza um cliente por ID de Forma Parcial")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cliente atualizado"),
        @ApiResponse(responseCode = "400", description = "Validação de Dados", content = @Content(schema = @Schema(implementation = Problem.class))),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    ClienteDTO updatePartial(@PathVariable Long id, @RequestBody Map<String, Object> campos);

}
