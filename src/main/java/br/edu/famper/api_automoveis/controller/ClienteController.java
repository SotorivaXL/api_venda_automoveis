package br.edu.famper.api_automoveis.controller;

import br.edu.famper.api_automoveis.dto.ClienteDto;
import br.edu.famper.api_automoveis.exception.ResourceNotFoundException;
import br.edu.famper.api_automoveis.model.Cliente;
import br.edu.famper.api_automoveis.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@Slf4j
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar todos os clientes", description = "Obtém todos os clientes do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    public List<ClienteDto> getAllClientes() {
        log.info("Buscando todos os clientes");
        return clienteService.getAllClientes();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar cliente por ID", description = "Obtém um cliente específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando cliente por ID: {}", id);
        return ResponseEntity.ok().body(clienteService.getClienteById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Salvar cliente", description = "Cadastra um novo cliente no banco de dados")
    public Cliente createCliente(@RequestBody ClienteDto clienteDto) {
        log.info("Cadastro de cliente: {}", clienteDto);
        return clienteService.saveCliente(clienteDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualizar cliente", description = "Atualiza um cliente existente no banco de dados")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable(name = "id") Long id, @RequestBody ClienteDto clienteDto) throws ResourceNotFoundException {
        log.info("Atualizando cliente com ID: {}", id);
        return ResponseEntity.ok(clienteService.editCliente(id, clienteDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remover cliente", description = "Remove um cliente do banco de dados")
    public Map<String, Boolean> deleteCliente(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Deletando cliente com ID: {}", id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", clienteService.deleteCliente(id));
        return response;
    }
}
