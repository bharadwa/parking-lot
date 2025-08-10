package com.lot.enums;

public enum PaymentMode {

    CASH,
    CARD,
    UPI,
    WALLET,
    NET_BANKING,
    CRYPTOCURRENCY;

    @Override
    public String toString() {
        return name().toLowerCase().replace('_', ' ');
    }
}
