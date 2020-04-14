package com.oocl;

public class ParkingTicket {
    private ParkingLot parkingLot;

    public ParkingTicket() {
    }

    public ParkingTicket(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
}
