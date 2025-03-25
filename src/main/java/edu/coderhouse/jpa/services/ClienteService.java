package edu.coderhouse.jpa.services;

import edu.coderhouse.jpa.model.Cliente;
import edu.coderhouse.jpa.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

  @Autowired
  private ClienteRepository clienteRepository;

  public ClienteService()
  {}

  public Cliente crear(Cliente cliente){return (Cliente) this.clienteRepository.save(cliente); }

  public Optional<Cliente> buscarPorID(Integer id) {return this.clienteRepository.findById(id);}

  public List<Cliente> traerTodos() {return  this.clienteRepository.findAll();}
}