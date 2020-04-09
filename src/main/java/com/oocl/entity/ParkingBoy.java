package com.oocl.entity;

import java.util.ArrayList;

public class ParkingBoy {
    public Ticket parkCar(Car car) {
        return new Ticket(car);
    }
}
