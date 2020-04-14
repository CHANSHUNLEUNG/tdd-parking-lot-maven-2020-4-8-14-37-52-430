package com.oocl.entity;

import com.oocl.exception.FullParkingTicket;

import java.util.Comparator;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(ParkingLot... parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public Ticket parkCar(Car car) {
        ParkingLot selectedParkingLot = this.getParkingLotList().stream()
                .max(Comparator.comparing(parkingLot ->
                        new Double(parkingLot.MAX_POSITION - parkingLot.getCarList().size()) / parkingLot.MAX_POSITION))
                .get();

        if (selectedParkingLot == null) {
            throw new FullParkingTicket(FullParkingTicket.FULL_POSITION_ERROR);
        }
        return selectedParkingLot.park(car);
    }
}
