package br.edu.famper.api_automoveis.repository;

import br.edu.famper.api_automoveis.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}
