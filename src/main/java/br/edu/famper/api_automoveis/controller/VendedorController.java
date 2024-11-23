package br.edu.famper.api_automoveis.controller;

import br.edu.famper.api_automoveis.dto.VendedorDto;
import br.edu.famper.api_automoveis.exception.ResourceNotFoundException;
import br.edu.famper.api_automoveis.model.Vendedor;
import br.edu.famper.api_automoveis.service.VendedorService;
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
@RequestMapping("/api/vendedores")
@RequiredArgsConstructor
@Slf4j
public class VendedorController {

    private final VendedorService vendedorService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar todos os vendedores", description = "Obtém todos os vendedores do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    public List<VendedorDto> getAllVendedores() {
        log.info("Buscando todos os vendedores");
        return vendedorService.getAllVendedores();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar vendedor por ID", description = "Obtém um vendedor específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Vendedor não encontrado")
    })
    public ResponseEntity<VendedorDto> getVendedorById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando vendedor por ID: {}", id);
        return ResponseEntity.ok().body(vendedorService.getVendedorById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Salvar vendedor", description = "Cadastra um novo vendedor no banco de dados")
    public Vendedor createVendedor(@RequestBody VendedorDto vendedorDto) {
        log.info("Cadastro de vendedor: {}", vendedorDto);
        return vendedorService.saveVendedor(vendedorDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualizar vendedor", description = "Atualiza um vendedor existente no banco de dados")
    public ResponseEntity<VendedorDto> updateVendedor(@PathVariable(name = "id") Long id, @RequestBody VendedorDto vendedorDto) throws ResourceNotFoundException {
        log.info("Atualizando vendedor com ID: {}", id);
        return ResponseEntity.ok(vendedorService.editVendedor(id, vendedorDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remover vendedor", description = "Remove um vendedor do banco de dados")
    public Map<String, Boolean> deleteVendedor(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Deletando vendedor com ID: {}", id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", vendedorService.deleteVendedor(id));
        return response;
    }
}
