package br.edu.famper.api_automoveis.repository;

import br.edu.famper.api_automoveis.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {
}
