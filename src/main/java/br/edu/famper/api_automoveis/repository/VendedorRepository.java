package br.edu.famper.api_automoveis.repository;

import br.edu.famper.api_automoveis.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
}