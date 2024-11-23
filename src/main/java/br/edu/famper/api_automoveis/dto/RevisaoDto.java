package br.edu.famper.api_automoveis.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RevisaoDto {

    @Schema(description = "ID da Revisão", example = "1", title = "id")
    private Long id;

    @Schema(description = "Descrição da Revisão", example = "Troca de óleo e filtros", title = "descricao", maxLength = 150)
    private String descricao;

    @Schema(description = "Data da Revisão", example = "2024-11-22", title = "dataRevisao")
    private LocalDate dataRevisao;

    @Schema(description = "ID do Carro relacionado à Revisão", example = "10", title = "carroId")
    private Long carroId;
}
