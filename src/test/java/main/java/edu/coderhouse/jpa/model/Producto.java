package main.java.edu.coderhouse.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(
    name = "producto"
)
public class Producto {
  public int getId() {
    return id;
  }

  @Id
  @GeneratedValue(
      strategy = GenerationType.IDENTITY
  )
  private int id;

  public String getDescripcion() {
    return descripcion;
  }

  public String getCodigo() {
    return codigo;
  }

  public int getStock() {
    return stock;
  }

  @Column(
      name = "descripcion"
  )
  private String descripcion;
  @Column(
      name = "codigo"
  )
  private String codigo;
  @Column(
      name = "stock"
  )
  private int stock;
  @Column(
      name = "precio"
  )
  private double precio;

  public Producto(String descripcion, String codigo, int stock, double precio) {
    this.descripcion = descripcion;
    this.codigo = codigo;
    this.stock = stock;
    this.precio = precio;
  }

  public Producto() {
  }

  public double getPrecio() {
    return this.precio;
  }

  public void setPrecio(double precio) {
    this.precio = precio;
  }

  public String toString() {
    return "Producto{id=" + this.id + ", codigo='" + this.codigo + "', descripcion='" + this.descripcion + "', precio=" + this.precio + ", stock=" + this.stock + "}";
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public void actualizarStock(int cantidad){
    setStock(this.stock - cantidad);
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
}