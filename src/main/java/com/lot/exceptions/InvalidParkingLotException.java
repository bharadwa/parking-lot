package com.lot.exceptions;

public class InvalidParkingLotException extends Exception {


    public InvalidParkingLotException(String message) {
        super(message);
    }

    public InvalidParkingLotException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidParkingLotException(Throwable cause) {
        super(cause);
    }
}
