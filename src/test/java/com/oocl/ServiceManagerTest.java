package com.oocl;

import com.oocl.common.ErrorMsg;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        ParkingTicket parkingTicket = serviceManager.assignParkingBoyToPark(car);

        Assert.assertNotNull(parkingTicket);
    }

    @Test
    public void should_throw_exception_given_that_boy_on_management_list_is_not_available_to_park_car(){
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ErrorMsg.ERROR_MSG_OF_NOT_ENOUGH_POSITION);

        ServiceManager serviceManager = new ServiceManager(Collections.singletonList(parkingLot));
        ParkingBoy parkingBoy = new ParkingBoy(Collections.singletonList(new ParkingLot(1)));
        parkingBoy.park(car);
        serviceManager.assignParkingBoyToManagementList(parkingBoy);

        serviceManager.assignParkingBoyToPark(car);
    }

    @Test
    public void should_assign_parking_boy_to_fetch_car_given_that_boy_is_on_management_list(){
        ServiceManager serviceManager = new ServiceManager(Collections.singletonList(parkingLot));
        ParkingBoy parkingBoy = new ParkingBoy(Collections.singletonList(parkingLot));

        serviceManager.assignParkingBoyToManagementList(parkingBoy);
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Optional<Car> fetchedCar = serviceManager.assignParkingBoyToFetch(parkingTicket);

        Assert.assertEquals(Optional.of(car), fetchedCar);
    }

    @Test
    public void should_throw_exception_given_that_no_boy_is_on_management_list(){
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ErrorMsg.ERROR_MSG_OF_NO_PARKING_BOY);

        ServiceManager serviceManager = new ServiceManager(Collections.singletonList(parkingLot));

        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicket.setParkingLot(parkingLot);
        serviceManager.assignParkingBoyToFetch(parkingTicket);
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
        ParkingTicket parkingTicket = parkingLot.park(car);
        parkingTicket.setParkingLot(parkingLot);
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
        serviceManager.assignParkingBoyToPark(car);
    }

    @Test
    public void should_return_exception_msg_when_fetch_with_incorrect_ticket() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ErrorMsg.ERROR_MSG_OF_UNRECOGNIZED_PARKING_TICKET);
        List<ParkingLot> parkingLotList = Collections.singletonList(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car = new Car();
        ServiceManager serviceManager = new ServiceManager(parkingLotList);
        ParkingTicket parkingTicket = parkingLot.park(car);;
        ParkingTicket incorrectParkingTicket = new ParkingTicket();

        serviceManager.assignParkingBoyToManagementList(parkingBoy);
        serviceManager.assignParkingBoyToFetch(incorrectParkingTicket);
    }

    @Test
    public void should_return_exception_msg_when_fetch_with_no_ticket() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ErrorMsg.ERROR_MSG_OF_NO_PARKING_TICKET);
        List<ParkingLot> parkingLotList = Collections.singletonList(new ParkingLot());
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        ServiceManager serviceManager = new ServiceManager(parkingLotList);

        serviceManager.assignParkingBoyToManagementList(parkingBoy);
        serviceManager.assignParkingBoyToFetch(null);
    }

    @Test
    public void should_return_exception_msg_when_fetch_with_used_ticket() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ErrorMsg.ERROR_MSG_OF_UNRECOGNIZED_PARKING_TICKET);

        List<ParkingLot> parkingLotList = Collections.singletonList(parkingLot);
        ServiceManager serviceManager = new ServiceManager(parkingLotList);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car = new Car();


        serviceManager.assignParkingBoyToManagementList(parkingBoy);
        ParkingTicket parkingTicket = parkingLot.park(car);
        parkingTicket.setParkingLot(parkingLot);
        parkingBoy.fetch(parkingTicket);
        serviceManager.assignParkingBoyToFetch(parkingTicket);
    }
}
