package br.edu.famper.api_automoveis.controller;

import br.edu.famper.api_automoveis.dto.VendaDto;
import br.edu.famper.api_automoveis.exception.ResourceNotFoundException;
import br.edu.famper.api_automoveis.model.Venda;
import br.edu.famper.api_automoveis.service.VendaService;
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
@RequestMapping("/api/vendas")
@RequiredArgsConstructor
@Slf4j
public class VendaController {

    private final VendaService vendaService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar todas as vendas", description = "Obtém todas as vendas do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    public List<VendaDto> getAllVendas() {
        log.info("Buscando todas as vendas");
        return vendaService.getAllVendas();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar venda por ID", description = "Obtém uma venda específica pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Venda não encontrada")
    })
    public ResponseEntity<VendaDto> getVendaById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando venda por ID: {}", id);
        return ResponseEntity.ok().body(vendaService.getVendaById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Salvar venda", description = "Cadastra uma nova venda no banco de dados")
    public Venda createVenda(@RequestBody VendaDto vendaDto) throws ResourceNotFoundException {
        log.info("Cadastro de venda: {}", vendaDto);
        return vendaService.saveVenda(vendaDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualizar venda", description = "Atualiza uma venda existente no banco de dados")
    public ResponseEntity<VendaDto> updateVenda(@PathVariable(name = "id") Long id, @RequestBody VendaDto vendaDto) throws ResourceNotFoundException {
        log.info("Atualizando venda com ID: {}", id);
        return ResponseEntity.ok(vendaService.editVenda(id, vendaDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remover venda", description = "Remove uma venda do banco de dados")
    public Map<String, Boolean> deleteVenda(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Deletando venda com ID: {}", id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", vendaService.deleteVenda(id));
        return response;
    }
}
