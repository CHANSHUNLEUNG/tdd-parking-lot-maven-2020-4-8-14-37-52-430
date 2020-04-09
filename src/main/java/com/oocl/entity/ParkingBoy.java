package com.oocl.entity;

public class ParkingBoy {

    public Ticket parkCar(ParkingLot parkingLot, Car car) {
        Ticket ticket = new Ticket(parkingLot,car);
        parkingLot.addTicket(ticket);

        return ticket;
    }

    public Car fetchCar(Ticket firstTicket) {
        firstTicket.getParkingLot().getTicketList().remove(firstTicket);
        return firstTicket.getCar();
    }
}
