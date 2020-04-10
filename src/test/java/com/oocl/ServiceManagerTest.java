package com.oocl;

import com.oocl.common.ErrorMsg;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ServiceManagerTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private ParkingLot parkingLot;
    private Car car;

    @Before
    public void setup() {
        parkingLot = new ParkingLot();
        car = new Car();
    }

    @Test
    public void should_add_parking_boy_to_management_list_when_assign(){
        ServiceManager serviceManager = new ServiceManager(Collections.singletonList(parkingLot));
        ParkingBoy parkingBoy = new ParkingBoy(Collections.singletonList(parkingLot));

        serviceManager.assignParkingBoyToManagementList(parkingBoy);

        Assert.assertTrue(serviceManager.getParkingBoyManagementList().contains(parkingBoy));

    }

    @Test
    public void should_assign_parking_boy_to_park_car_given_that_boy_is_on_management_list(){
        ServiceManager serviceManager = new ServiceManager(Collections.singletonList(parkingLot));
        ParkingBoy parkingBoy = new ParkingBoy(Collections.singletonList(parkingLot));

        serviceManager.assignParkingBoyToManagementList(parkingBoy);
        ParkingTicket parkingTicket = serviceManager.assignParkingBoyToPark(parkingBoy, car);

        Assert.assertNotNull(parkingTicket);
    }

    @Test
    public void should_not_assign_parking_boy_to_park_car_given_that_boy_is_not_on_management_list(){
        ServiceManager serviceManager = new ServiceManager(Collections.singletonList(parkingLot));
        ParkingBoy parkingBoy = new ParkingBoy(Collections.singletonList(parkingLot));

        ParkingTicket parkingTicket = serviceManager.assignParkingBoyToPark(parkingBoy, car);

        Assert.assertNull(parkingTicket);
    }

    @Test
    public void should_assign_parking_boy_to_fetch_car_given_that_boy_is_on_management_list(){
        ServiceManager serviceManager = new ServiceManager(Collections.singletonList(parkingLot));
        ParkingBoy parkingBoy = new ParkingBoy(Collections.singletonList(parkingLot));

        serviceManager.assignParkingBoyToManagementList(parkingBoy);
        ParkingTicket parkingTicket = serviceManager.assignParkingBoyToPark(parkingBoy, car);
        Car fetchedCar = serviceManager.assignParkingBoyToFetch(parkingBoy, parkingTicket);

        Assert.assertEquals(car, fetchedCar);
    }

    @Test
    public void should_not_assign_parking_boy_to_fetch_car_given_that_boy_is_not_on_management_list(){
        ServiceManager serviceManager = new ServiceManager(Collections.singletonList(parkingLot));
        ParkingBoy parkingBoy = new ParkingBoy(Collections.singletonList(parkingLot));

        ParkingTicket parkingTicket = serviceManager.assignParkingBoyToPark(parkingBoy, car);
        Car fetchedCar = serviceManager.assignParkingBoyToFetch(parkingBoy, parkingTicket);

        Assert.assertNull(fetchedCar);
    }

    @Test
    public void should_park_car_to_parking_lot() {
        List<ParkingLot> parkingLotList = Collections.singletonList(parkingLot);
        ServiceManager serviceManager = new ServiceManager(parkingLotList);

        ParkingTicket parkingTicket = serviceManager.park(car);
        Car fetchedCarFromParkingLot = parkingTicket.getParkingLot().fetch(parkingTicket);

        Assert.assertEquals(fetchedCarFromParkingLot, car);
    }

    @Test
    public void should_fetch_car_when_fetch_from_parking_lot_with_correct_ticket() {
        //given
        List<ParkingLot> parkingLotList = Collections.singletonList(parkingLot);
        ServiceManager serviceManager = new ServiceManager(parkingLotList);
        Car car = new Car();
        ParkingTicket parkingTicket = new ParkingTicket(parkingLot);

        parkingLot.park(car, parkingTicket);
        //when
        Car fetchedCarFromParkingLot = serviceManager.fetch(parkingTicket);
        //then
        Assert.assertEquals(fetchedCarFromParkingLot, car);
    }

    @Test
    public void should_return_exception_msg_when_parking_lot_capacity_is_full() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ErrorMsg.ERROR_MSG_OF_NOT_ENOUGH_POSITION);
        List<ParkingLot> parkingLotList = Collections.singletonList(new ParkingLot(1));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        ServiceManager serviceManager = new ServiceManager(parkingLotList);

        parkingBoy.park(car);
        serviceManager.assignParkingBoyToManagementList(parkingBoy);
        serviceManager.assignParkingBoyToPark(parkingBoy, car);
    }

    @Test
    public void should_return_exception_msg_when_fetch_with_incorrect_ticket() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ErrorMsg.ERROR_MSG_OF_UNRECOGNIZED_PARKING_TICKET);
        List<ParkingLot> parkingLotList = Collections.singletonList(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car = new Car();
        ServiceManager serviceManager = new ServiceManager(parkingLotList);
        ParkingTicket parkingTicket = new ParkingTicket(parkingLot);
        ParkingTicket incorrectParkingTicket = new ParkingTicket(parkingLot);

        parkingLot.park(car, parkingTicket);
        serviceManager.assignParkingBoyToManagementList(parkingBoy);
        serviceManager.assignParkingBoyToFetch(parkingBoy, incorrectParkingTicket);
    }

    @Test
    public void should_return_exception_msg_when_fetch_with_no_ticket() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ErrorMsg.ERROR_MSG_OF_NO_PARKING_TICKET);
        List<ParkingLot> parkingLotList = Collections.singletonList(new ParkingLot());
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        ServiceManager serviceManager = new ServiceManager(parkingLotList);

        serviceManager.assignParkingBoyToManagementList(parkingBoy);
        serviceManager.assignParkingBoyToFetch(parkingBoy, null);
    }

    @Test
    public void should_return_exception_msg_when_fetch_with_used_ticket() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ErrorMsg.ERROR_MSG_OF_UNRECOGNIZED_PARKING_TICKET);

        List<ParkingLot> parkingLotList = Collections.singletonList(parkingLot);
        ServiceManager serviceManager = new ServiceManager(parkingLotList);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car = new Car();
        ParkingTicket parkingTicket = new ParkingTicket(parkingLot);

        serviceManager.assignParkingBoyToManagementList(parkingBoy);
        parkingLot.park(car, parkingTicket);
        parkingBoy.fetch(parkingTicket);
        serviceManager.assignParkingBoyToFetch(parkingBoy, parkingTicket);
    }
}
