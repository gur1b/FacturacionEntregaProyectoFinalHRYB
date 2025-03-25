package main.java.edu.coderhouse.jpa.repository;

import main.java.edu.coderhouse.jpa.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {
}
