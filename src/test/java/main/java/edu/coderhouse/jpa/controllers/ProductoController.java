package main.java.edu.coderhouse.jpa.controllers;

import main.java.edu.coderhouse.jpa.model.Producto;
import main.java.edu.coderhouse.jpa.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/producto")
public class ProductoController {
  @Autowired
  private ProductoService productoService;

  @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<?> getProductoById(@PathVariable(name = "id") int id) {
    Optional<Producto> producto = productoService.buscarPorID(id);
    if(producto.isPresent()){
      return ResponseEntity.ok(producto);
    }else{
      return ResponseEntity.notFound().build();
    }
  }
  @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<?> guardarProducto(@RequestBody Producto producto) {
    try {
      Producto productoGuardado =  productoService.crear(producto);
      return ResponseEntity.created(URI.create("")).body(productoGuardado);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.internalServerError().body(null);
    }
  }}

