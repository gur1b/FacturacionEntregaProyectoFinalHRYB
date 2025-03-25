package main.java.edu.coderhouse.jpa.services;

import main.java.edu.coderhouse.jpa.model.Producto;
import main.java.edu.coderhouse.jpa.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

  @Autowired
  private ProductoRepository productoRepository;

  public ProductoService()
  {}

  public Producto crear(Producto producto){return (Producto) this.productoRepository.save(producto); }

  public Optional<Producto> buscarPorID(Integer id) {return this.productoRepository.findById(id);}

  public List<Producto> traerTodos() {return  this.productoRepository.findAll();}
}