package com.oocl.entity;

import com.oocl.exception.FullParkingTicketException;

import java.util.Comparator;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(ParkingLot... parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public Ticket parkCar(Car car) {
        ParkingLot selectedParkingLot = this.getParkingLotList().stream()
                .max(Comparator.comparing(parkingLot ->
                        (double) (ParkingLot.MAX_POSITION - parkingLot.getCarList().size()) / ParkingLot.MAX_POSITION))
                .orElseThrow(() -> new FullParkingTicketException(FullParkingTicketException.FULL_POSITION_ERROR));

        return selectedParkingLot.park(car);
    }
}
