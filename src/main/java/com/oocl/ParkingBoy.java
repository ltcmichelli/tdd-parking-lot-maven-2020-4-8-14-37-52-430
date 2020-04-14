package com.oocl;

import com.oocl.common.ErrorMsg;

import java.util.List;

public class ParkingBoy {
    protected List<ParkingLot> parkingLotList;

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public ParkingTicket park(Car car) {
        if (car == null) {
            return null;
        }

        ParkingLot selectedParkingLot = selectParkingLot();

        if (selectedParkingLot != null) {
            ParkingTicket parkingTicket = selectedParkingLot.park(car);
            parkingTicket.setParkingLot(selectedParkingLot);
            return parkingTicket;
        }

        throw new IllegalArgumentException(ErrorMsg.ERROR_MSG_OF_NOT_ENOUGH_POSITION);

    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            throw new IllegalArgumentException(ErrorMsg.ERROR_MSG_OF_NO_PARKING_TICKET);
        }
        if (parkingTicket.getParkingLot() == null){
            throw new IllegalArgumentException(ErrorMsg.ERROR_MSG_OF_UNRECOGNIZED_PARKING_TICKET);
        }
        ParkingLot designatedParkingLot = parkingTicket.getParkingLot();
        Car fetchedCar = designatedParkingLot.fetch(parkingTicket);
        if (fetchedCar == null) {
            throw new IllegalArgumentException(ErrorMsg.ERROR_MSG_OF_UNRECOGNIZED_PARKING_TICKET);
        }
        return fetchedCar;
    }

    public ParkingLot selectParkingLot() {
        return parkingLotList.stream().filter(lot -> !lot.isFull()).findFirst().orElse(null);
    }
}
