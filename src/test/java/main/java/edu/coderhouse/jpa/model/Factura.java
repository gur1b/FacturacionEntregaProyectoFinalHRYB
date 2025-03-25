package main.java.edu.coderhouse.jpa.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
    name = "Factura"
)
public class Factura {
  public int getId() {
    return id;
  }

  @Id
  @GeneratedValue(
      strategy = GenerationType.IDENTITY
  )
  private int id;
  @ManyToOne
  @JoinColumn(
      name = "id_cliente",
      nullable = false
  )
  private Cliente cliente;
  @Column(
      name = "fecha_creacion"
  )
  private LocalDateTime fechaCreacion;
  @Column(
      name = "total"
  )
  private double total;

  public List<DetalleFactura> getDetalles() {
    return detalles;
  }

  public void setDetalles(List<DetalleFactura> detalles) {
    this.detalles = detalles;
  }

  @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<DetalleFactura> detalles;


  public Factura(Cliente cliente, double total) {
    this.cliente = cliente;
    this.total = total;
    this.fechaCreacion = LocalDateTime.now();
  }

  public Factura() {
  }

  public Cliente getCliente() {
    return this.cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public LocalDateTime getFechaCreacion() {
    return this.fechaCreacion;
  }

  public void setFechaCreacion(LocalDateTime fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  public void agregarDetalles (DetalleFactura detalleFactura){
    this.detalles.add(detalleFactura);
  }

  public double getTotal() {
    return this.total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  @Override
  public String toString() {
    return "Factura [id=" + id + ", clienteId=" + (cliente != null ? cliente.getId() : "null") + ", total=" + total + "]";
  }

  public double calcularTotal() {
    if (detalles == null){return 0;} else {
    return this.total = detalles.stream()
            .mapToDouble(d -> d.getPrecio() * d.getCantidad())
            .sum();}
  }
}
