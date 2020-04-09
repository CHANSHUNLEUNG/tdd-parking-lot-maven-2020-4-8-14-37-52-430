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
        ticketList = new ArrayList<Ticket>();
        parkingBoys = new ArrayList<ParkingBoy>();
        carList = new ArrayList<Car>();
    }


    public ArrayList<ParkingBoy> getParkingBoys() {
        return parkingBoys;
    }

    public void setParkingBoys(ArrayList<ParkingBoy> parkingBoys) {
        this.parkingBoys = parkingBoys;
    }

    public ArrayList<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(ArrayList<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public void addTicket(Ticket ticket) {
        this.ticketList.add(ticket);
    }

    public ArrayList<Car> getCarList() {
        return carList;
    }

    public void setCarList(ArrayList<Car> carList) {
        this.carList = carList;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }

    public void addCar(Car car) {
        this.carList.add(car);
    }

    public boolean isFull(){
        return this.carList.size() >= MAX_POSITION;
    }

    public Ticket park(Car car) {
        Ticket ticket = new Ticket(this, car);
        this.addTicket(ticket);
        this.addCar(car);
        return ticket;
    }
}
