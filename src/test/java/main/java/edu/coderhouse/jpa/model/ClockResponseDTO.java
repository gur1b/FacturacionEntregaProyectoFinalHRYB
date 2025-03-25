package main.java.edu.coderhouse.jpa.model;

//Uso de Data Transfer Object (DTO) para evitar transferencia de datos innecesarios.
public class ClockResponseDTO {
    private String currentDateTime; // Solo nos interesa este campo

    // Getter y Setter
    public String getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(String currentDateTime) {
        this.currentDateTime = currentDateTime;
    }
}
