package main.java.edu.coderhouse.jpa.controllers;

import main.java.edu.coderhouse.jpa.services.DateTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Conexión con la API externa
@RestController
@RequestMapping("/time")
public class ClockController {
    @Autowired
    private DateTimeService dateTimeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCurrentTime() {
        String response = String.valueOf(dateTimeService.getCurrentTime());
        return ResponseEntity.ok(response);
    }
}