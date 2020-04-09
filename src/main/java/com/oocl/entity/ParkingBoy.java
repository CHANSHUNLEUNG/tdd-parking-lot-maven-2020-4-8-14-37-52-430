package com.oocl.entity;

public class ParkingBoy {

    public Ticket parkCar(ParkingLot parkingLot, Car car) {
        return new Ticket(parkingLot, car);
    }
}
