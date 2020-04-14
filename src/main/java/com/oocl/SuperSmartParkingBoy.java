package com.oocl;

import java.util.Comparator;
import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public ParkingLot selectParkingLot() {
        return parkingLotList.stream().max(Comparator.comparing(ParkingLot::getAvailablePositionRate)).orElse(null);
    }
}
