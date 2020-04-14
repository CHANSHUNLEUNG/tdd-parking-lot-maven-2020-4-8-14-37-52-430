package com.oocl.exception;

public class FullParkingTicket extends RuntimeException {
    public static final String FULL_POSITION_ERROR = "Not enough position.";

    public FullParkingTicket(String message) {
        super(message);
    }
}
