package br.edu.famper.api_automoveis.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "carro")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "modelo", length = 150, nullable = false)
    private String modelo;

    @Column(name = "marca", length = 100, nullable = false)
    private String marca;

    @Column(name = "ano", nullable = false)
    private int ano;

    @OneToMany(mappedBy = "carro", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Revisao> revisoes;

    @OneToOne(mappedBy = "carro", cascade = CascadeType.ALL)
    private Venda venda;
}
