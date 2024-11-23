package br.edu.famper.api_automoveis.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "vendedor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", length = 150, nullable = false)
    private String nome;

    @Column(name = "cnpj", length = 14, unique = true)
    private String cnpj;

    @Column(name = "email", length = 150, unique = true)
    private String email;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Column(name = "endereco", length = 200)
    private String endereco;

    @OneToMany(mappedBy = "vendedor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Venda> vendas;
}
