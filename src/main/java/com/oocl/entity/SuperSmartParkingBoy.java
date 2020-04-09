package com.oocl.entity;

import com.oocl.exception.FullParkingTicket;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(ParkingLot... parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public Ticket parkCar(Car car) throws FullParkingTicket {
        return super.parkCar(car);
    }
}
