package main.java.edu.coderhouse.jpa.controllers;

import main.java.edu.coderhouse.jpa.exceptions.RecursoNoEncontrado;
import main.java.edu.coderhouse.jpa.exceptions.StockInsuficiente;
import main.java.edu.coderhouse.jpa.model.DetalleFactura;
import main.java.edu.coderhouse.jpa.model.Factura;
import main.java.edu.coderhouse.jpa.services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/factura")
public class FacturaController {
  @Autowired
  private FacturaService facturaService;

  @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<?> getFacturaById(@PathVariable(name = "id") int id) {
    Optional<Factura> facturaOpt = facturaService.buscarPorID(id);
    if (facturaOpt.isPresent()) {
      Factura factura = facturaOpt.get();
      int totalProductos = factura.getDetalles().stream()
              .mapToInt(DetalleFactura::getCantidad)
              .sum();

      // Crear respuesta con la factura y el total de productos
      Map<String, Object> response = Map.of(
              "factura", factura,
              "totalProductos", totalProductos
      );
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
  @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<?> guardarFactura(@RequestBody Factura factura) {
    try {
      Factura facturaGuardada = facturaService.crear(factura);
      return ResponseEntity.created(URI.create("/factura/" + facturaGuardada.getId())).body(facturaGuardada);
    } catch (StockInsuficiente e) {
      return ResponseEntity.badRequest().body(
              Map.of(
                      "error", "Stock insuficiente",
                      "mensaje", e.getMessage(),
                      "timestamp", LocalDateTime.now().toString()
              )
      );
    } catch (RecursoNoEncontrado e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
              Map.of(
                      "error", "Recurso no encontrado",
                      "mensaje", e.getMessage(),
                      "timestamp", LocalDateTime.now().toString()
              )
      );
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(
              Map.of(
                      "error", "Error interno",
                      "mensaje", "Ocurrió un error inesperado",
                      "timestamp", LocalDateTime.now().toString()
              )
      );
    }
  }
  }
