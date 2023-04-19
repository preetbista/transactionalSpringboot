package com.flight.flightservice;

import com.flight.flightservice.dto.FlightBookingAcknowledgment;
import com.flight.flightservice.dto.FlightBookingRequest;
import com.flight.flightservice.service.FlightBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableTransactionManagement
@RestController
public class FlightServiceApplication {
    @Autowired
    private FlightBookingService service;

    @PostMapping("/bookFlightTicket")
    public FlightBookingAcknowledgment bookFlightTicket(@RequestBody FlightBookingRequest request) {
        return service.bookFlightTicket(request);
    }

    public static void main(String[] args) {
        SpringApplication.run(FlightServiceApplication.class, args);
    }

}
