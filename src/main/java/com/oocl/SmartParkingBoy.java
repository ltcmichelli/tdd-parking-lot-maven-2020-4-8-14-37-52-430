package com.oocl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public ParkingLot selectParkingLot() {
        return parkingLotList.stream().max(Comparator.comparing(ParkingLot::getAvailableSpace)).orElse(null);
    }
}
