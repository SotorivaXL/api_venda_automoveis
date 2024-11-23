package br.edu.famper.api_automoveis.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendaDto {

    @Schema(description = "ID da Venda", example = "1", title = "id")
    private Long id;

    @Schema(description = "ID do Carro vendido", example = "15", title = "carroId")
    private Long carroId;

    @Schema(description = "ID do Cliente que realizou a compra", example = "20", title = "clienteId")
    private Long clienteId;

    @Schema(description = "ID do Vendedor responsável pela venda", example = "5", title = "vendedorId")
    private Long vendedorId;

    @Schema(description = "Data da Venda", example = "2024-11-22", title = "dataVenda")
    private LocalDate dataVenda;

    @Schema(description = "Valor da Venda", example = "50000.00", title = "valorVenda")
    private Double valorVenda;
}
