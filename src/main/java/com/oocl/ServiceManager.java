package com.oocl;

import java.util.ArrayList;
import java.util.List;

public class ServiceManager extends ParkingBoy{
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

    public ParkingTicket assignParkingBoyToPark(ParkingBoy parkingBoy, Car car){
        if (parkingBoyManagementList.contains(parkingBoy)){
            return parkingBoy.park(car);
        }
        return null;
    }
}
