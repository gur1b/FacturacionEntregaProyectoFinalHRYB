package main.java.edu.coderhouse.jpa.services;

import main.java.edu.coderhouse.jpa.model.DetalleFactura;
import main.java.edu.coderhouse.jpa.model.Factura;
import main.java.edu.coderhouse.jpa.model.Producto;
import main.java.edu.coderhouse.jpa.repository.DetalleFacturaRepository;
import main.java.edu.coderhouse.jpa.repository.FacturaRepository;
import main.java.edu.coderhouse.jpa.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleFacturaService {

  @Autowired
  private DetalleFacturaRepository detalleFacturaRepository;

  @Autowired
  private FacturaRepository facturaRepository;
  @Autowired
  private ProductoRepository productoRepository;

  public DetalleFacturaService()
  {}

  //Metodo para que en el POST solo necesitemos el id de factura y producto para crear el Detalle
  public DetalleFactura crear(DetalleFactura detalleFactura)
  {
    Factura factura = facturaRepository.findById(detalleFactura.getFactura().getId()).orElseThrow(() -> new RuntimeException("Factura no encontrada"));
    Producto producto = productoRepository.findById(detalleFactura.getProducto().getId()).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    detalleFactura.setFactura(factura);
    detalleFactura.setProducto(producto);
    return detalleFacturaRepository.save(detalleFactura); }

  public Optional<DetalleFactura> buscarPorID(Integer id) {return this.detalleFacturaRepository.findById(id);}

  public List<DetalleFactura> traerTodos() {return  this.detalleFacturaRepository.findAll();}
}
