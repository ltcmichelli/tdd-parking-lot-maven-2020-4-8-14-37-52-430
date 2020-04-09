package com.oocl;

import com.oocl.common.ErrorMsg;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ParkingBoyTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_park_car_to_parking_lot() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        ParkingTicket parkingTicket = parkingBoy.park(car);
        Car fetchedCarFromParkingLot = parkingLot.fetch(parkingTicket);

        Assert.assertEquals(fetchedCarFromParkingLot, car);
    }

    @Test
    public void should_fetch_car_when_fetch_from_parking_lot_with_correct_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingLot.park(car, parkingTicket);
        Car fetchedCarFromParkingLot = parkingBoy.fetch(parkingTicket);

        Assert.assertEquals(fetchedCarFromParkingLot, car);
    }

    @Test
    public void should_not_park_car_when_parking_lot_capacity_is_full() {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        parkingBoy.park(car);
        ParkingTicket newParkingTicket = parkingBoy.park(car);

        Assert.assertNull(newParkingTicket);

    }

    @Test
    public void should_return_exception_msg_when_fetch_with_incorrect_ticket() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ErrorMsg.ERROR_MSG_OF_UNRECOGNIZED_PARKING_TICKET);
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        ParkingTicket parkingTicket = new ParkingTicket();
        ParkingTicket incorrectParkingTicket = new ParkingTicket();

        parkingLot.park(car, parkingTicket);
        parkingBoy.fetch(incorrectParkingTicket);
    }

    @Test
    public void should_return_exception_msg_when_fetch_with_no_ticket() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ErrorMsg.ERROR_MSG_OF_NO_PARKING_TICKET);
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        parkingBoy.fetch(null);
    }
}
