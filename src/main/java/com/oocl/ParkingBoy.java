package com.oocl;

public class ParkingBoy {
    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = parkingLot.park(car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        Car fetchedCar = parkingLot.fetch(parkingTicket);
        return fetchedCar;
    }
}
