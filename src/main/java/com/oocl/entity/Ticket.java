package com.oocl.entity;

public class Ticket {

    private Car car;
    private ParkingLot parkingLot;

    public Ticket(ParkingLot parkingLot, Car car) {
        this.car = car;
        this.parkingLot = parkingLot;
    }

    public Car getCar() {
        return car;
    }

    ParkingLot getParkingLot() {
        return parkingLot;
    }

}
