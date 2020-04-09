package com.oocl.entity;

import com.oocl.exception.UnrecognizedParkingTicket;

public class ParkingBoy {
    public static final String UNRECOGNIZED_PARKING_TICKET_ERROR = "Unrecognized parking ticket";
    private ParkingLot parkingLot;

    public ParkingBoy() {
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket parkCar(ParkingLot parkingLot, Car car) {
        boolean isParkingLotFull = parkingLot.getCarList().size() >= ParkingLot.MAX_POSITION;
        if (isParkingLotFull) {
            return null;
        }

        Ticket ticket = new Ticket(parkingLot, car);
        parkingLot.addTicket(ticket);
        parkingLot.addCar(car);

        return ticket;
    }

    public Car fetchCar(Ticket ticket) throws UnrecognizedParkingTicket{
        boolean isTicketCorrect = ticket.getParkingLot().getCarList().contains(ticket.getCar());
        if (!isTicketCorrect) {
            return null;
        }
        ticket.getParkingLot().getTicketList().remove(ticket);
        ticket.getParkingLot().getCarList().remove(ticket.getCar());
        return ticket.getCar();

    }

    public Car fetchCar() {
        return null;
    }
}
