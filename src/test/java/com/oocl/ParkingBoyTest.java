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

public class ParkingBoyTest {
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
    public void should_park_car_to_parking_lot() {
        //given
        List<ParkingLot> parkingLotList = Collections.singletonList(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        //when
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Car fetchedCarFromParkingLot = parkingTicket.getParkingLot().fetch(parkingTicket);

        Assert.assertEquals(fetchedCarFromParkingLot, car);
    }

    @Test
    public void should_fetch_car_when_fetch_from_parking_lot_with_correct_ticket() {
        //given
        List<ParkingLot> parkingLotList = Collections.singletonList(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);
        parkingTicket.setParkingLot(parkingLot);
        //when
        Car fetchedCarFromParkingLot = parkingBoy.fetch(parkingTicket);
        //then
        Assert.assertEquals(fetchedCarFromParkingLot, car);
    }

    @Test
    public void should_return_exception_msg_when_parking_lot_capacity_is_full() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ErrorMsg.ERROR_MSG_OF_NOT_ENOUGH_POSITION);
        List<ParkingLot> parkingLotList = Collections.singletonList(new ParkingLot(1));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car = new Car();

        parkingBoy.park(car);
        ParkingTicket newParkingTicket = parkingBoy.park(car);

        Assert.assertNull(newParkingTicket);
    }

    @Test
    public void should_return_exception_msg_when_fetch_with_incorrect_ticket() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ErrorMsg.ERROR_MSG_OF_UNRECOGNIZED_PARKING_TICKET);
        List<ParkingLot> parkingLotList = Collections.singletonList(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);
        parkingTicket.setParkingLot(parkingLot);
        ParkingTicket incorrectParkingTicket = new ParkingTicket();

        parkingBoy.fetch(incorrectParkingTicket);
    }

    @Test
    public void should_return_exception_msg_when_fetch_with_no_ticket() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ErrorMsg.ERROR_MSG_OF_NO_PARKING_TICKET);
        List<ParkingLot> parkingLotList = Collections.singletonList(new ParkingLot());
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        parkingBoy.fetch(null);
    }

    @Test
    public void should_return_exception_msg_when_fetch_with_used_ticket() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ErrorMsg.ERROR_MSG_OF_UNRECOGNIZED_PARKING_TICKET);
        List<ParkingLot> parkingLotList = Collections.singletonList(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);
        parkingTicket.setParkingLot(parkingLot);

        parkingBoy.fetch(parkingTicket);
        parkingBoy.fetch(parkingTicket);
    }

    @Test
    public void should_park_car_to_second_parking_lot_when_the_first_one_is_full() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(3);
        List<ParkingLot> parkingLotList = Arrays.asList(firstParkingLot, secondParkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car = new Car();
        ParkingTicket parkingTicket = firstParkingLot.park(car);
        ParkingTicket actualParkingTicket = parkingBoy.park(car);

        Assert.assertEquals(secondParkingLot, actualParkingTicket.getParkingLot());
    }

    @Test
    public void should_park_car_to_first_parking_lot_when_the_second_one_is_full() {
        ParkingLot firstParkingLot = new ParkingLot(3);
        ParkingLot secondParkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLotList = Arrays.asList(firstParkingLot, secondParkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car = new Car();
        ParkingTicket parkingTicket = firstParkingLot.park(car);
        ParkingTicket actualParkingTicket = parkingBoy.park(car);

        Assert.assertEquals(firstParkingLot, actualParkingTicket.getParkingLot());
    }
}
