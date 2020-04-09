package com.oocl.entity;

public class ParkingBoy {
    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
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

    public Car fetchCar(Ticket ticket) {
        boolean isTicketCorrect = ticket.getParkingLot().getCarList().contains(ticket.getCar());
        if (isTicketCorrect) {
            ticket.getParkingLot().getTicketList().remove(ticket);
            ticket.getParkingLot().getCarList().remove(ticket.getCar());
            return ticket.getCar();
        }
        return null;

    }

    public Car fetchCar() {
        return null;
    }
}
