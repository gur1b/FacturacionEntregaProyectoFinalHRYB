package main.java.edu.coderhouse.jpa.services;

import main.java.edu.coderhouse.jpa.model.ClockResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
public class DateTimeService {

   @Autowired
   private DateRestApi dateRestApi;

    public LocalDateTime getCurrentTime() {
        try {
            ClockResponseDTO response = dateRestApi.getCurrentDateTime();
            return parseDateTime(response.getCurrentDateTime());
        } catch (Exception e) {
            return LocalDateTime.now();
        }
    }

    //Uso de un parse para que no haya error de formato. Se parsea a un LocalDateTime
    private LocalDateTime parseDateTime(String dateTimeStr) {
        return OffsetDateTime.parse(dateTimeStr)
                .toLocalDateTime();
    }
}
