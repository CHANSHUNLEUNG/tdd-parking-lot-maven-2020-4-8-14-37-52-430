package com.oocl.exception;

public class FullParkingTicketException extends RuntimeException {
    public static final String FULL_POSITION_ERROR = "Not enough position.";

    public FullParkingTicketException(String message) {
        super(message);
    }
}
