package com.oocl.entity;

import java.util.ArrayList;

public class ParkingLot {
    private String parkingLotId;

    public ParkingLot(String parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    private ArrayList<ParkingBoy> parkingBoys;
    private ArrayList<Ticket> ticketList;

    public ArrayList<ParkingBoy> getParkingBoys() {
        return parkingBoys;
    }

    public void setParkingBoys(ArrayList<ParkingBoy> parkingBoys) {
        this.parkingBoys = parkingBoys;
    }
}
