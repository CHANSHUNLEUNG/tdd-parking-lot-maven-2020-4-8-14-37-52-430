package com.oocl.exception;

public class UnrecognizedParkingTicket extends Exception{
    public static final String Wrong_TICKET_ERROR = "Unrecognized parking ticket";
    public static final String NO_TICKET_ERROR = "Please provide your parking ticket.";

    public UnrecognizedParkingTicket(String message) {
        super(message);
    }
}
