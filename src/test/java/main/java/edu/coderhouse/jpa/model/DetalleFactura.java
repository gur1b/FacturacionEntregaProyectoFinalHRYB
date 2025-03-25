package main.java.edu.coderhouse.jpa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(
    name = "DETALLE_FACTURA"
)
public class DetalleFactura {
  @Id
  @GeneratedValue(
      strategy = GenerationType.IDENTITY
  )
  private int id;

  public int getId() {
    return id;
  }

  public void setFactura(Factura factura) {
    this.factura = factura;
  }

  public Factura getFactura() {
    return factura;
  }

  @ManyToOne
  @JoinColumn(name = "id_factura", nullable = false)
  @JsonBackReference
  private Factura factura;


  @Column(
      name = "cantidad"
  )
  private int cantidad;
  @ManyToOne
  @JoinColumn(
      name = "producto_id",
      nullable = false
  )
  private Producto producto;
  @Column(
      name = "precio"
  )
  private double precio;

  @Override
  public String toString() {
    return "DetalleFactura [id=" + id + ", productoId=" + (producto != null ? producto.getId() : "null") + ", cantidad=" + cantidad + "]";
  }

  public DetalleFactura(Factura factura, int cantidad, Producto producto, double precio) {
    this.cantidad = cantidad;
    this.factura = factura;
    this.producto = producto;
    this.precio = precio;
  }

  public DetalleFactura() {
  }

  public Producto getProducto() {
    return this.producto;
  }

  public void setProducto(Producto producto) {
    this.producto = producto;
  }


  public void setRecibo(Factura recibo) {
    this.factura = recibo;
  }

  public int getCantidad() {
    return this.cantidad;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }

  public double getPrecio() {
    return this.precio;
  }

  public void setPrecio(double precio) {
    this.precio = precio;
  }
}

