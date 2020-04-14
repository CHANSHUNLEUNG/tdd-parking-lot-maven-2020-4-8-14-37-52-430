package com.oocl.entity;

import com.oocl.exception.FullParkingTicketException;
import com.oocl.exception.UnrecognizedParkingTicketException;

import java.util.ArrayList;
import java.util.Arrays;

public class ParkingBoy {
    private ArrayList<ParkingLot> parkingLotList = new ArrayList<>();

    public ArrayList<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public ParkingBoy(ParkingLot... parkingLotList) {
        this.parkingLotList.addAll(Arrays.asList(parkingLotList));
    }

    public Ticket parkCar(Car car) {
        ParkingLot selectedParkingLot = this.parkingLotList.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .findFirst()
                .orElseThrow(() -> new FullParkingTicketException(FullParkingTicketException.FULL_POSITION_ERROR));
        return selectedParkingLot.park(car);

    }

    public Car fetchCar(Ticket ticket) {
        boolean isTicketCorrect = ticket.getParkingLot().getCarList().contains(ticket.getCar());
        if (!isTicketCorrect) {
            throw new UnrecognizedParkingTicketException(UnrecognizedParkingTicketException.Wrong_TICKET_ERROR);
        }
        ticket.getParkingLot().getTicketList().remove(ticket);
        ticket.getParkingLot().getCarList().remove(ticket.getCar());
        return ticket.getCar();
    }

    public void fetchCar() throws UnrecognizedParkingTicketException {
        throw new UnrecognizedParkingTicketException(UnrecognizedParkingTicketException.NO_TICKET_ERROR);
    }
}
