package br.edu.famper.api_automoveis.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendedorDto {

    @Schema(description = "ID do Vendedor", example = "1", title = "id")
    private Long id;

    @Schema(description = "Nome do Vendedor", example = "João Silva", title = "nome", maxLength = 150)
    private String nome;

    @Schema(description = "CNPJ do Vendedor", example = "12345678000100", title = "cnpj", maxLength = 14)
    private String cnpj;

    @Schema(description = "Email do Vendedor", example = "joao.silva@email.com", title = "email", maxLength = 150)
    private String email;

    @Schema(description = "Telefone do Vendedor", example = "(11) 98765-4321", title = "telefone", maxLength = 20)
    private String telefone;

    @Schema(description = "Endereço do Vendedor", example = "Rua das Flores, 123", title = "endereco", maxLength = 200)
    private String endereco;
}
