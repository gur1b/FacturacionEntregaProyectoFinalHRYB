package main.java.edu.coderhouse.jpa.exceptions;

//Uso de Excepciones para manejar los errores y que se muestren en la respuesta del POST

public class StockInsuficiente extends RuntimeException {
    public StockInsuficiente(String mensaje) {
        super(mensaje);
    }
}