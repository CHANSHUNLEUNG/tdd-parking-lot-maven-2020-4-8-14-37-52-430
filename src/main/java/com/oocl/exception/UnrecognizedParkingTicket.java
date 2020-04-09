package com.oocl.exception;

public class UnrecognizedParkingTicket extends Exception{
    public UnrecognizedParkingTicket() {
    }

    public UnrecognizedParkingTicket(String message) {
        super(message);
    }
}
