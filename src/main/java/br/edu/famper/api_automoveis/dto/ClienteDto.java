package br.edu.famper.api_automoveis.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDto {

    @Schema(description = "ID do Cliente", example = "1", title = "id")
    private Long id;

    @Schema(description = "Nome do Cliente", example = "Maria Oliveira", title = "nome", maxLength = 150)
    private String nome;

    @Schema(description = "CPF do Cliente", example = "12345678901", title = "cpf", maxLength = 11)
    private String cpf;

    @Schema(description = "Email do Cliente", example = "maria.oliveira@email.com", title = "email", maxLength = 150)
    private String email;

    @Schema(description = "Telefone do Cliente", example = "(21) 91234-5678", title = "telefone", maxLength = 20)
    private String telefone;

    @Schema(description = "Endereço do Cliente", example = "Avenida Central, 456", title = "endereco", maxLength = 200)
    private String endereco;
}
