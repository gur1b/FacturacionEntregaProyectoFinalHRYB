package main.java.edu.coderhouse.jpa.controllers;

import java.net.URI;
import java.util.Optional;

import main.java.edu.coderhouse.jpa.model.Cliente;
import main.java.edu.coderhouse.jpa.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
  @Autowired
  private ClienteService clienteService;

  @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<?> getClienteById(@PathVariable(name = "id") int id) {
    Optional<Cliente> cliente = clienteService.buscarPorID(id);
    if(cliente.isPresent()){
      return ResponseEntity.ok(cliente);
    }else{
      return ResponseEntity.notFound().build();
    }
  }
  @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<?> guardarCliente(@RequestBody Cliente cliente) {
    try {
      Cliente clienteGuardado =  clienteService.crear(cliente);
      return ResponseEntity.created(URI.create("")).body(clienteGuardado);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.internalServerError().body(null);
    }
  }}
