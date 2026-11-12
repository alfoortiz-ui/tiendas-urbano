package com.tiendasurbano.payment;

public interface PaymentGateway {
    PaymentResult authorize(String orderReference, long amountInCents);

    record PaymentResult(boolean approved, String reference) {
    }
}
