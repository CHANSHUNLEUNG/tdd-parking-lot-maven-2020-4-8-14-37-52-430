package com.oocl.entity;

import java.util.ArrayList;

public class ParkingLot {
    private ArrayList<Ticket> ticketList;
    private ArrayList<Car> carList;
    public static final int MAX_POSITION = 10;

    public ParkingLot() {
        ticketList = new ArrayList<>();
        carList = new ArrayList<>();
    }

    public ArrayList<Ticket> getTicketList() {
        return ticketList;
    }

    private void addTicket(Ticket ticket) {
        this.ticketList.add(ticket);
    }

    public ArrayList<Car> getCarList() {
        return carList;
    }

    private void addCar(Car car) {
        this.carList.add(car);
    }

    boolean isFull() {
        return this.carList.size() >= MAX_POSITION;
    }

    Ticket park(Car car) {
        Ticket ticket = new Ticket(this, car);
        this.addTicket(ticket);
        this.addCar(car);
        return ticket;
    }
}
