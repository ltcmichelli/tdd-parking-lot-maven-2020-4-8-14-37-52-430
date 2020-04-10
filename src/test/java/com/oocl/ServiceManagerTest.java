package com.oocl;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ServiceManagerTest {

    @Test
    public void should_add_parking_boy_to_management_list_when_assign(){
        ParkingLot parkingLot = new ParkingLot();
        ServiceManager serviceManager = new ServiceManager(Arrays.asList(parkingLot));
        ParkingBoy parkingBoy = new ParkingBoy(Arrays.asList(parkingLot));

        serviceManager.assignParkingBoyToManagementList(parkingBoy);

        Assert.assertTrue(serviceManager.getParkingBoyManagementList().contains(parkingBoy));

    }

    @Test
    public void should_assign_parking_boy_to_park_car_given_that_boy_is_on_management_list(){
        ParkingLot parkingLot = new ParkingLot();
        ServiceManager serviceManager = new ServiceManager(Arrays.asList(parkingLot));
        ParkingBoy parkingBoy = new ParkingBoy(Arrays.asList(parkingLot));
        Car car = new Car();

        ParkingTicket parkingTicket = serviceManager.assignParkingBoyToPark(parkingBoy, car);

        Assert.assertNull(parkingTicket);
    }
}
