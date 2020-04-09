package com.oocl.entity;

public class Ticket {

    private String carNumber;
    private ParkingLot parkingLot;
    public Ticket(ParkingLot parkingLot, Car car) {
        this.carNumber = car.getCarNumber();
        this.parkingLot = parkingLot;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
}
