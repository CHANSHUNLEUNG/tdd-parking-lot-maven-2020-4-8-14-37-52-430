package com.oocl.entity;

import java.util.ArrayList;

public class ParkingLot {
    private String parkingLotName;
    private ArrayList<Ticket> ticketList;
    private ArrayList<ParkingBoy> parkingBoys;
    private ArrayList<Car> carList;
    public static final int MAX_POSITION = 10;

    public ParkingLot(String parkingLotName) {
        this.parkingLotName = parkingLotName;
        ticketList = new ArrayList<>();
        parkingBoys = new ArrayList<>();
        carList = new ArrayList<>();
    }

    public ArrayList<Ticket> getTicketList() {
        return ticketList;
    }

    public void addTicket(Ticket ticket) {
        this.ticketList.add(ticket);
    }

    public ArrayList<Car> getCarList() {
        return carList;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public void addCar(Car car) {
        this.carList.add(car);
    }

    public boolean isFull() {
        return this.carList.size() >= MAX_POSITION;
    }

    public Ticket park(Car car) {
        Ticket ticket = new Ticket(this, car);
        this.addTicket(ticket);
        this.addCar(car);
        return ticket;
    }
}
