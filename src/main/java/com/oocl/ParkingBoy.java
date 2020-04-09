package com.oocl;

import com.oocl.common.ErrorMsg;

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
        if (parkingTicket == null){
            throw new IllegalArgumentException(ErrorMsg.ERROR_MSG_OF_NO_PARKING_TICKET);
        }

        Car fetchedCar = parkingLot.fetch(parkingTicket);
        if (fetchedCar == null){
            throw new IllegalArgumentException(ErrorMsg.ERROR_MSG_OF_UNRECOGNIZED_PARKING_TICKET);
        } return fetchedCar;
    }
}
