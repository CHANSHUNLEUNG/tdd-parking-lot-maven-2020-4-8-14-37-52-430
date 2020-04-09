package com.oocl.entity;

import com.oocl.exception.FullParkingTicket;

import java.util.Comparator;
import java.util.stream.IntStream;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(ParkingLot... parkingLot) {
        super(parkingLot);
    }

    @Override
    public Ticket parkCar(Car car) throws FullParkingTicket {
        ParkingLot selectedParkingLot = this.getParkingLotList().stream()
                .max(Comparator.comparing(parkingLot -> parkingLot.MAX_POSITION - parkingLot.getCarList().size())).get();
        System.out.println(selectedParkingLot.getParkingLotName());
        if (selectedParkingLot == null) {
            throw new FullParkingTicket(FullParkingTicket.FULL_POSITION_ERROR);
        }
        return selectedParkingLot.park(car);
    }
}
