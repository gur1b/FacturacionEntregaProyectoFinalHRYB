package main.java.edu.coderhouse.jpa.controllers;

import main.java.edu.coderhouse.jpa.model.DetalleFactura;
import main.java.edu.coderhouse.jpa.services.DetalleFacturaService;
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
@RequestMapping("/detalleFactura")
public class DetalleFacturaController {
  @Autowired
  private DetalleFacturaService detalleFacturaService;

  @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<?> getDetalleFacturaByID(@PathVariable(name = "id") int id) {
    Optional<DetalleFactura> detalle = detalleFacturaService.buscarPorID(id);
    if(detalle.isPresent()){
      return ResponseEntity.ok(detalle);
    }else{
      return ResponseEntity.notFound().build();
    }
  }
  @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<?> guardarDetalleFactura(@RequestBody DetalleFactura detalle) {
    try {
      DetalleFactura detalleGuardado =  detalleFacturaService.crear(detalle);
      return ResponseEntity.created(URI.create("")).body(detalleGuardado);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.internalServerError().body(null);
    }
  }}
