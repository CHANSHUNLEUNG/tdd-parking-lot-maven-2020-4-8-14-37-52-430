package com.oocl.entity;

import java.util.ArrayList;

public class ParkingLot {
    private String parkingLotName;
    private ArrayList<Ticket> ticketList;
    private ArrayList<ParkingBoy> parkingBoys;


    public ParkingLot(String parkingLotName) {
        this.parkingLotName = parkingLotName;
        ticketList = new ArrayList<Ticket>();
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
}
