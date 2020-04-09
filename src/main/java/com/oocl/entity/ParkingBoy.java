package com.oocl.entity;

public class ParkingBoy {

    public Ticket parkCar(ParkingLot parkingLot, Car car) {
        Ticket ticket = new Ticket(parkingLot,car);
        parkingLot.addTicket(ticket);
        parkingLot.addCar(car);

        return ticket;
    }

    public Car fetchCar(Ticket ticket) {

        System.out.println(ticket.getParkingLot().getTicketList().size());
        ticket.getParkingLot().getTicketList().remove(ticket);
        System.out.println(ticket.getParkingLot().getTicketList().size());

        System.out.println(ticket.getParkingLot().getCarList().size());
        ticket.getParkingLot().getCarList().remove(ticket.getCar());
        System.out.println(ticket.getParkingLot().getCarList().size());

        return ticket.getCar();
    }
}
