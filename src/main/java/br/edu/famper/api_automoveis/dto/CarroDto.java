package br.edu.famper.api_automoveis.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarroDto {

    @Schema(description = "ID do Carro", example = "1", title = "id")
    private Long id;

    @Schema(description = "Modelo do Carro", example = "Civic", title = "modelo", maxLength = 150)
    private String modelo;

    @Schema(description = "Marca do Carro", example = "Honda", title = "marca", maxLength = 100)
    private String marca;

    @Schema(description = "Ano de Fabricação do Carro", example = "2022", title = "ano")
    private int ano;

    @Schema(description = "Lista de Revisões do Carro", title = "revisoes")
    private Set<RevisaoDto> revisoes;
}
