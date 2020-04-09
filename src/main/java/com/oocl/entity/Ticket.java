package com.oocl.entity;

public class Ticket {

    private String carNumber;
    public Ticket(Car car) {
        this.carNumber = car.getCarNumber();
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
}
