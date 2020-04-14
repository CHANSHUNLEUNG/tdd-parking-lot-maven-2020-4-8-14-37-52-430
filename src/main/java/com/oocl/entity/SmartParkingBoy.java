package com.oocl.entity;

import com.oocl.exception.FullParkingTicketException;

import java.util.Comparator;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(ParkingLot... parkingLot) {
        super(parkingLot);
    }

    @Override
    public Ticket parkCar(Car car) {
        ParkingLot selectedParkingLot = this.getParkingLotList().stream()
                .max(Comparator.comparing(parkingLot -> ParkingLot.MAX_POSITION - parkingLot.getCarList().size()))
                .orElseThrow(() -> new FullParkingTicketException(FullParkingTicketException.FULL_POSITION_ERROR));
        return selectedParkingLot.park(car);
    }
}
