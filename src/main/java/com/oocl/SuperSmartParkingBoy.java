package com.oocl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SuperSmartParkingBoy extends ParkingBoy{
    public SuperSmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public Optional<ParkingLot> selectParkingLot() {
        return parkingLotList.stream().max(Comparator.comparing(ParkingLot::getAvailablePositionRate));
    }
}
