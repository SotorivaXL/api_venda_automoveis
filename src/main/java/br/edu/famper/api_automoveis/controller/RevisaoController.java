package br.edu.famper.api_automoveis.controller;

import br.edu.famper.api_automoveis.dto.RevisaoDto;
import br.edu.famper.api_automoveis.exception.ResourceNotFoundException;
import br.edu.famper.api_automoveis.model.Revisao;
import br.edu.famper.api_automoveis.service.RevisaoService;
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
@RequestMapping("/api/revisoes")
@RequiredArgsConstructor
@Slf4j
public class RevisaoController {

    private final RevisaoService revisaoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar todas as revisões", description = "Obtém todas as revisões do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    public List<RevisaoDto> getAllRevisoes() {
        log.info("Buscando todas as revisões");
        return revisaoService.getAllRevisoes();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar revisão por ID", description = "Obtém uma revisão específica pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Revisão não encontrada")
    })
    public ResponseEntity<RevisaoDto> getRevisaoById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando revisão por ID: {}", id);
        return ResponseEntity.ok().body(revisaoService.getRevisaoById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Salvar revisão", description = "Cadastra uma nova revisão no banco de dados")
    public Revisao createRevisao(@RequestBody RevisaoDto revisaoDto) throws ResourceNotFoundException {
        log.info("Cadastro de revisão: {}", revisaoDto);
        return revisaoService.saveRevisao(revisaoDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualizar revisão", description = "Atualiza uma revisão existente no banco de dados")
    public ResponseEntity<RevisaoDto> updateRevisao(@PathVariable(name = "id") Long id, @RequestBody RevisaoDto revisaoDto) throws ResourceNotFoundException {
        log.info("Atualizando revisão com ID: {}", id);
        return ResponseEntity.ok(revisaoService.editRevisao(id, revisaoDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remover revisão", description = "Remove uma revisão do banco de dados")
    public Map<String, Boolean> deleteRevisao(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Deletando revisão com ID: {}", id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", revisaoService.deleteRevisao(id));
        return response;
    }
}
