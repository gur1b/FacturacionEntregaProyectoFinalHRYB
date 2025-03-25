package main.java.edu.coderhouse.jpa.services;

import main.java.edu.coderhouse.jpa.exceptions.RecursoNoEncontrado;
import main.java.edu.coderhouse.jpa.exceptions.StockInsuficiente;
import main.java.edu.coderhouse.jpa.model.Cliente;
import main.java.edu.coderhouse.jpa.model.DetalleFactura;
import main.java.edu.coderhouse.jpa.model.Factura;
import main.java.edu.coderhouse.jpa.model.Producto;
import main.java.edu.coderhouse.jpa.repository.ClienteRepository;
import main.java.edu.coderhouse.jpa.repository.FacturaRepository;
import main.java.edu.coderhouse.jpa.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {

  @Autowired
  private FacturaRepository facturaRepository;

  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private ProductoRepository productoRepository;

  @Autowired
  private DateTimeService dateTimeService;

  public FacturaService()
  {}

  public Factura crear(Factura factura) {

    factura.setCliente(buscarCliente(factura));
    try {
      // Intenta obtener la fecha desde la API
      factura.setFechaCreacion(dateTimeService.getCurrentTime());
    } catch (Exception e) {
      // Si hay error, usa la LocalDateTime
      factura.setFechaCreacion(LocalDateTime.now());
    }

    //Setear el detalle de la factura.
    if (factura.getDetalles() != null) {
      for (DetalleFactura detalle : factura.getDetalles()) {
        Producto producto = buscarProducto(detalle);
        manejoStock(detalle, producto);

        detalle.setProducto(producto);
        detalle.setPrecio(producto.getPrecio());
        detalle.setFactura(factura);
      }
    }

    factura.calcularTotal();
    return facturaRepository.save(factura);
  }

  public Cliente buscarCliente(Factura factura)
  { Cliente cliente = clienteRepository.findById(factura.getCliente().getId())
          .orElseThrow(() -> new RecursoNoEncontrado("Cliente con ID " + factura.getCliente().getId() + " no encontrado"));
  return cliente;}

  public Producto buscarProducto(DetalleFactura detalle)
  { Producto producto = productoRepository.findById(detalle.getProducto().getId())
          .orElseThrow(() -> new RecursoNoEncontrado("Producto con ID " + detalle.getProducto().getId() + " no encontrado"));
      return producto;
  }

  //Lanza excepción si el stock no es el suficiente y actualiza el stock.
  public  void manejoStock(DetalleFactura detalle, Producto producto)
  {// Verificar stock
    if (detalle.getCantidad() > producto.getStock()) {
      throw new StockInsuficiente(
              "Stock insuficiente para el producto: " + producto.getDescripcion() +
                      " (ID: " + producto.getId() + "). Stock disponible: " + producto.getStock()
      );
    }

    producto.actualizarStock(detalle.getCantidad());
    productoRepository.save(producto);
    }

  public Optional<Factura> buscarPorID(Integer id) {return this.facturaRepository.findById(id);}

  public List<Factura> traerTodos() {return  this.facturaRepository.findAll();}
}