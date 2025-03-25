package main.java.edu.coderhouse.jpa.exceptions;

//Uso de Excepciones para manejar los errores y que se muestren en la respuesta del POST

public class RecursoNoEncontrado extends  RuntimeException{
    public RecursoNoEncontrado(String mensaje){
        super(mensaje);
    }
}
