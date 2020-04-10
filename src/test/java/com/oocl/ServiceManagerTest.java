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

        serviceManager.assignParkingBoyToManagementList(parkingBoy);
        ParkingTicket parkingTicket = serviceManager.assignParkingBoyToPark(parkingBoy, car);

        Assert.assertNotNull(parkingTicket);
    }

    @Test
    public void should_not_assign_parking_boy_to_park_car_given_that_boy_is_not_on_management_list(){
        ParkingLot parkingLot = new ParkingLot();
        ServiceManager serviceManager = new ServiceManager(Arrays.asList(parkingLot));
        ParkingBoy parkingBoy = new ParkingBoy(Arrays.asList(parkingLot));
        Car car = new Car();

        ParkingTicket parkingTicket = serviceManager.assignParkingBoyToPark(parkingBoy, car);

        Assert.assertNull(parkingTicket);
    }

    @Test
    public void should_assign_parking_boy_to_fetch_car_given_that_boy_is_on_management_list(){
        ParkingLot parkingLot = new ParkingLot();
        ServiceManager serviceManager = new ServiceManager(Arrays.asList(parkingLot));
        ParkingBoy parkingBoy = new ParkingBoy(Arrays.asList(parkingLot));
        Car car = new Car();

        serviceManager.assignParkingBoyToManagementList(parkingBoy);
        ParkingTicket parkingTicket = serviceManager.assignParkingBoyToPark(parkingBoy, car);
        Car fetchedCar = serviceManager.assignParkingBoyToFetch(parkingBoy, parkingTicket);

        Assert.assertEquals(car, fetchedCar);
    }
}
