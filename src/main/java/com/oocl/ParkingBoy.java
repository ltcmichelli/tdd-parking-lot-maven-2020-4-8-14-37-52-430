package com.oocl;

public class ParkingBoy {
    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        if (this.parkingLot.isFull() || car == null) {
            return null;
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        this.parkingLot.park(car, parkingTicket);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        Car fetchedCar = parkingLot.fetch(parkingTicket);
        return fetchedCar;
    }
}
