package br.edu.famper.api_automoveis.controller;
import br.edu.famper.api_automoveis.dto.CarroDto;
import br.edu.famper.api_automoveis.exception.ResourceNotFoundException;
import br.edu.famper.api_automoveis.model.Carro;
import br.edu.famper.api_automoveis.service.CarroService;
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
@RequestMapping("/api/carros")
@RequiredArgsConstructor
@Slf4j
public class CarroController {

    private final CarroService carroService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar todos os carros", description = "Obtém todos os carros do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    public List<CarroDto> getAllCarros() {
        log.info("Buscando todos os carros");
        return carroService.getAllCarros();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar carro por ID", description = "Obtém um carro específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Carro não encontrado")
    })
    public ResponseEntity<CarroDto> getCarroById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando carro por ID: {}", id);
        return ResponseEntity.ok().body(carroService.getCarroById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Salvar carro", description = "Cadastra um novo carro no banco de dados")
    public Carro createCarro(@RequestBody CarroDto carroDto) {
        log.info("Cadastro de carro: {}", carroDto);
        return carroService.saveCarro(carroDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualizar carro", description = "Atualiza um carro existente no banco de dados")
    public ResponseEntity<CarroDto> updateCarro(@PathVariable(name = "id") Long id, @RequestBody CarroDto carroDto) throws ResourceNotFoundException {
        log.info("Atualizando carro com ID: {}", id);
        return ResponseEntity.ok(carroService.editCarro(id, carroDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remover carro", description = "Remove um carro do banco de dados")
    public Map<String, Boolean> deleteCarro(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Deletando carro com ID: {}", id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", carroService.deleteCarro(id));
        return response;
    }
}
