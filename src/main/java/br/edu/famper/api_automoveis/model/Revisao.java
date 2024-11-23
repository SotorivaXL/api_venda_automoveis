package br.edu.famper.api_automoveis.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "revisao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Revisao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "descricao", length = 150, nullable = false)
    private String descricao;

    @Column(name = "data_revisao", nullable = false)
    private LocalDate dataRevisao;

    @ManyToOne
    @JoinColumn(name = "carro_id", nullable = false)
    private Carro carro;
}
