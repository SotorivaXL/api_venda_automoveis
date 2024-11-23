package br.edu.famper.api_automoveis.repository;

import br.edu.famper.api_automoveis.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
