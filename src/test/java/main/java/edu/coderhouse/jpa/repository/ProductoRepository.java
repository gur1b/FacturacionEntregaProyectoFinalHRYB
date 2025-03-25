package main.java.edu.coderhouse.jpa.repository;

import main.java.edu.coderhouse.jpa.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
