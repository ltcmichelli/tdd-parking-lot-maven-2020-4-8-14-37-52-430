package com.oocl;

import com.oocl.common.ErrorMsg;

import java.util.ArrayList;
import java.util.List;

public class ServiceManager extends ParkingBoy {
    private List<ParkingBoy> parkingBoyManagementList = new ArrayList<>();

    public ServiceManager(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }


    public void assignParkingBoyToManagementList(ParkingBoy parkingBoy) {
        parkingBoyManagementList.add(parkingBoy);
    }

    public List<ParkingBoy> getParkingBoyManagementList() {
        return this.parkingBoyManagementList;
    }

    public ParkingTicket assignParkingBoyToPark(Car car) {
        ParkingBoy parkingBoy =
                parkingBoyManagementList.stream().filter(boy -> boy.selectParkingLot().isPresent()).findFirst()
                        .orElseThrow(() -> new IllegalArgumentException(ErrorMsg.ERROR_MSG_OF_NOT_ENOUGH_POSITION));

        return parkingBoy.park(car);
    }

    public Car assignParkingBoyToFetch(ParkingBoy parkingBoy, ParkingTicket parkingTicket) {
        if (parkingBoyManagementList.contains(parkingBoy)) {
            return parkingBoy.fetch(parkingTicket);
        }
        return null;
    }
}
