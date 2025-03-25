package main.java.edu.coderhouse.jpa.services;

import main.java.edu.coderhouse.jpa.model.ClockResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//Servicio para conexión con la API.
//Se usa un DTO para evitar transferencia de datos innecesarios.
@Service
public class DateRestApi {
    public ClockResponseDTO getCurrentDateTime() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://worldclockapi.com/api/json/utc/now";
        return restTemplate.getForObject(url, ClockResponseDTO.class);
    }
}
