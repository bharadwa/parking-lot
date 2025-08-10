package com.lot.models;

import java.util.Date;

public class Bill {

    private Long billId;

    private Ticket ticket;

    private Gate gate;

    private Operator operator;

    private Date exiteTime;

    private double amount ;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Date getExiteTime() {
        return exiteTime;
    }

    public void setExiteTime(Date exiteTime) {
        this.exiteTime = exiteTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
