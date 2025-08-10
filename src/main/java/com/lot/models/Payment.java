package com.lot.models;

import com.lot.enums.PaymentMode;
import com.lot.enums.PaymentStatus;

public class Payment {

    private long paymentId;

    private PaymentMode paymentMode;

    private double amount;

    private long paymentReferenceId;

    private PaymentStatus paymentStatus;

    private Bill bill;

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getPaymentReferenceId() {
        return paymentReferenceId;
    }

    public void setPaymentReferenceId(long paymentReferenceId) {
        this.paymentReferenceId = paymentReferenceId;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
