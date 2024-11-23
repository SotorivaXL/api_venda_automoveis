package br.edu.famper.api_automoveis.repository;

import br.edu.famper.api_automoveis.model.Revisao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RevisaoRepository extends JpaRepository<Revisao, Long> {
}
