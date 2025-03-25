package main.java.edu.coderhouse.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(
    name = "Cliente"
)
public class Cliente {
  public int getId() {
    return id;
  }

  @Id
  @GeneratedValue(
      strategy = GenerationType.IDENTITY
  )
  private int id;
  @Column(
      name = "nombre"
  )
  private String nombre;
  @Column(
      name = "apellido"
  )
  private String apellido;
  @Column(
      name = "numeroDNI"
  )
  private String numeroDocumento;

  public Cliente() {
  }

  public Cliente(String nombre, String apellido, String numeroDocumento) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.numeroDocumento = numeroDocumento;
  }

  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return this.apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getNumeroDocumento() {
    return this.numeroDocumento;
  }

  public void setNumeroDocumento(String numeroDocumento) {
    this.numeroDocumento = numeroDocumento;
  }

  public String toString() {
    return "Cliente{id=" + this.id + ", apellido='" + this.apellido + "', nombre='" + this.nombre + "', numerodni='" + this.numeroDocumento + "'}";
  }
}
