package com.practice.LLTS_payments;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

@DgsComponent
public class PaymentDataFetcher {

    @Autowired
    private PaymentRepository paymentRepository;

    @DgsQuery
    public List<Payment> getPaymentsByEmployeeId(Long employeeId) {
        return paymentRepository.findByEmployeeId(employeeId);
    }

    @DgsMutation
    public Payment processPayment(Long employeeId, BigDecimal amount) {
        Payment payment = new Payment();
        payment.setEmployeeId(employeeId);
        payment.setAmount(amount);
        payment.setStatus("Processed");
        return paymentRepository.save(payment);
    }
}