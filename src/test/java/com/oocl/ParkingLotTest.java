package com.oocl;

import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTest {

    @Test
    public void should_return_parking_ticket_when_park_car() {
        ParkingLot parkingLot = new ParkingLot();

        ParkingTicket parkingTicket = parkingLot.park(new Car());

        Assert.assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_fetch_car_with_parking_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        ParkingTicket parkingTicket = parkingLot.park(car);
        Car fetchedCar = parkingLot.fetch(parkingTicket);

        Assert.assertEquals(car, fetchedCar);
    }

    @Test
    public void should_return_no_car_when_fetch_car_with_wrong_parking_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        parkingLot.park(car);
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        Car fetchedCar = parkingLot.fetch(wrongParkingTicket);

        Assert.assertNull(fetchedCar);
    }

    @Test
    public void should_return_no_car_when_fetch_car_with_used_parking_ticket(){
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        ParkingTicket parkingTicket = parkingLot.park(car);
        parkingLot.fetch(parkingTicket);
        Car fetchedCarInSecondTime = parkingLot.fetch(parkingTicket);

        Assert.assertNull(fetchedCarInSecondTime);
    }

    @Test
    public void should_not_park_car_when_parking_lot_capacity_is_full(){
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setCapacity(10);

        ParkingTicket parkingTicket = parkingLot.park(new Car());
        Assert.assertNull(parkingTicket);

    }
}
