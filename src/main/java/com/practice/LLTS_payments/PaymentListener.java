package com.practice.LLTS_payments;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PaymentListener {

    Logger log = LoggerFactory.getLogger(PaymentListener.class);

    @Autowired
    private PaymentRepository paymentRepository;

    @RabbitListener(queues = "payment-queue")
    public void handleEmployeePayment(Long employeeId) {
        log.info("Employee payment received: " + employeeId);
        // Process payment for the employee
        Payment payment = new Payment();
        payment.setEmployeeId(employeeId);
        payment.setAmount(new BigDecimal("1000"));  // Example amount
        payment.setStatus("Processed");
        paymentRepository.save(payment);
    }
}
