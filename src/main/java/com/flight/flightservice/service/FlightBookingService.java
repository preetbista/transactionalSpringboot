package com.flight.flightservice.service;

import com.flight.flightservice.dto.FlightBookingAcknowledgment;
import com.flight.flightservice.dto.FlightBookingRequest;
import com.flight.flightservice.model.PassengerInfo;
import com.flight.flightservice.model.PaymentInfo;
import com.flight.flightservice.repository.PassengerInfoRepository;
import com.flight.flightservice.repository.PaymentInfoRepository;
import com.flight.flightservice.utils.PaymentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class FlightBookingService {
    @Autowired
    private PassengerInfoRepository passengerInfoRepository;
    @Autowired
    private PaymentInfoRepository paymentInfoRepository;
    @Transactional
    public FlightBookingAcknowledgment bookFlightTicket(FlightBookingRequest request){
        PassengerInfo passengerInfo = request.getPassengerInfo();
        passengerInfoRepository.save(passengerInfo);

        PaymentInfo paymentInfo = request.getPaymentInfo();

        PaymentUtils.validateCreditLimit(paymentInfo.getAccountNo(),passengerInfo.getFare());

        paymentInfo.setPassengerId(passengerInfo.getId());
        paymentInfo.setAmount(passengerInfo.getFare());
        paymentInfoRepository.save(paymentInfo);
        return new FlightBookingAcknowledgment("SUCCESS",passengerInfo.getFare(), UUID.randomUUID().toString().split("-")[0],passengerInfo);
    }
}
