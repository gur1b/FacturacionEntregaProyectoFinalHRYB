package edu.coderhouse.jpa.repository;

import edu.coderhouse.jpa.model.DetalleFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Integer> {
}
