package com.javatechie.tx;

import com.javatechie.tx.dto.FlightBookingAcknowledgement;
import com.javatechie.tx.dto.FlightBookingRequest;
import com.javatechie.tx.service.FlightBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableTransactionManagement
public class FlightServiceExampleApplication {

	/*
	 * Servizio per la scrittura della richiesta di booking nel database 
	 */
	@Autowired
	private FlightBookingService service;


	/*
	 * Richiesta post per booking nel database. 
	 * @param request Post request di booking in formato json da salvare nel db 
	 * @return Acknowledgement sullo stato di prenotazione del biglietto
	 */
	@PostMapping("/bookFlightTicket")
	public FlightBookingAcknowledgement bookFlightTicket(@RequestBody FlightBookingRequest request){
		return service.bookFlightTicket(request);
	}
	
	/*
	 * Metodo di prova per richiesta get
	 */
	@GetMapping("/saluta")
	public String saluta() {
		return "Hello world"; 
	}

	public static void main(String[] args) {
		SpringApplication.run(FlightServiceExampleApplication.class, args);
	}

}
