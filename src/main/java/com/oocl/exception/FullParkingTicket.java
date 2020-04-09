package com.oocl.exception;

public class FullParkingTicket extends Exception {
    public static final String FULL_POSITION_ERROR = "Not enough position.";
    public FullParkingTicket(String message) {
        super(message);
    }
}
