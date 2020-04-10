package com.oocl;

import com.oocl.common.ErrorMsg;

import java.util.List;
import java.util.Optional;

public class ParkingBoy {
    protected List<ParkingLot> parkingLotList;

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public ParkingTicket park(Car car) {
        if (car == null) {
            return null;
        }

        Optional<ParkingLot> selectedParkingLot = selectParkingLot();

        if (selectedParkingLot.isPresent()) {
            ParkingTicket parkingTicket = new ParkingTicket(selectedParkingLot.get());
            selectedParkingLot.get().park(car, parkingTicket);
            return parkingTicket;
        }

        throw new IllegalArgumentException(ErrorMsg.ERROR_MSG_OF_NOT_ENOUGH_POSITION);

    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            throw new IllegalArgumentException(ErrorMsg.ERROR_MSG_OF_NO_PARKING_TICKET);
        }
        ParkingLot designatedParkingLot = parkingTicket.getParkingLot();
        Car fetchedCar = designatedParkingLot.fetch(parkingTicket);
        if (fetchedCar == null) {
            throw new IllegalArgumentException(ErrorMsg.ERROR_MSG_OF_UNRECOGNIZED_PARKING_TICKET);
        }
        return fetchedCar;
    }

    public Optional<ParkingLot> selectParkingLot() {
        return parkingLotList.stream().filter(lot -> !lot.isFull()).findFirst();
    }
}
