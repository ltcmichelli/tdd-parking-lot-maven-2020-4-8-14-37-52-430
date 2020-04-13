package com.oocl;

import com.oocl.common.ErrorMsg;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    // TODO check if the list is null
    public ParkingTicket assignParkingBoyToPark(Car car) {
        ParkingBoy parkingBoy =
                parkingBoyManagementList.stream().filter(boy -> boy.selectParkingLot().isPresent()).findFirst()
                        .orElseThrow(() -> new IllegalArgumentException(ErrorMsg.ERROR_MSG_OF_NOT_ENOUGH_POSITION));

        return parkingBoy.park(car);
    }

    public Optional<Car> assignParkingBoyToFetch(ParkingTicket parkingTicket) {
        try{
            return parkingBoyManagementList.stream().map(boy -> boy.fetch(parkingTicket)).findFirst();
        }catch (IllegalArgumentException exception) {
            throw exception;
        }
    }
}
