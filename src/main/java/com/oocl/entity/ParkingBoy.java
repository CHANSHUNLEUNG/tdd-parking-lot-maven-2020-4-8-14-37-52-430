package com.oocl.entity;

import com.oocl.exception.FullParkingTicket;
import com.oocl.exception.UnrecognizedParkingTicket;

import java.util.ArrayList;
import java.util.Arrays;

public class ParkingBoy {
    private ArrayList<ParkingLot> parkingLotList = new ArrayList<ParkingLot>();

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
                .orElseThrow(() -> new FullParkingTicket(FullParkingTicket.FULL_POSITION_ERROR));
        return selectedParkingLot.park(car);

    }

    public Car fetchCar(Ticket ticket) {
        boolean isTicketCorrect = ticket.getParkingLot().getCarList().contains(ticket.getCar());
        if (!isTicketCorrect) {
            throw new UnrecognizedParkingTicket(UnrecognizedParkingTicket.Wrong_TICKET_ERROR);
        }
        ticket.getParkingLot().getTicketList().remove(ticket);
        ticket.getParkingLot().getCarList().remove(ticket.getCar());
        return ticket.getCar();
    }

    public Car fetchCar() throws UnrecognizedParkingTicket {
        throw new UnrecognizedParkingTicket(UnrecognizedParkingTicket.NO_TICKET_ERROR);
    }
}
