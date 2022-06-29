package com.javatechie.tx.service;

import com.javatechie.tx.dto.FlightBookingAcknowledgement;
import com.javatechie.tx.dto.FlightBookingRequest;
import com.javatechie.tx.entity.PassengerInfo;
import com.javatechie.tx.entity.PaymentInfo;
import com.javatechie.tx.repository.PassengerInfoRepository;
import com.javatechie.tx.repository.PaymentInfoRepository;
import com.javatechie.tx.utils.PaymentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class FlightBookingService {

	/*
	 * Repository per info passeggeri, utilizzata solo per i metodi ereditati. 
	 */
    @Autowired
    private PassengerInfoRepository passengerInfoRepository;

	/*
	 * Repository per info pagamenti, utilizzata solo per i metodi ereditati. 
	 */
    @Autowired
    private PaymentInfoRepository paymentInfoRepository;

    /*
     * Metodo transactional, se viene sollevata un'eccezione fa il rollback in automatico.
     * Permette di evitare incongruenze nei dati. 
     * Il booking viene portato a termine solo se nell'account selezionato c'è denaro sufficiente. Altrimenti non succede niente. 
     * @param request Richiesta contenente una passengerInfo e una PaymentInfo.
     */
    @Transactional//(readOnly = false,isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public FlightBookingAcknowledgement bookFlightTicket(FlightBookingRequest request) {

        PassengerInfo passengerInfo = request.getPassengerInfo();
        passengerInfo = passengerInfoRepository.save(passengerInfo);

        PaymentInfo paymentInfo = request.getPaymentInfo();

        PaymentUtils.validateCreditLimit(paymentInfo.getAccountNo(), passengerInfo.getFare());

        paymentInfo.setPassengerId(passengerInfo.getPId());
        paymentInfo.setAmount(passengerInfo.getFare());
        paymentInfoRepository.save(paymentInfo);

        return new FlightBookingAcknowledgement(
        		"SUCCESS", 
        		passengerInfo.getFare(), 
        		UUID.randomUUID().toString().split("-")[0], 
        		passengerInfo);
    }
}
