package com.oocl;

import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTest {

    @Test
    public void should_return_parking_ticket_when_park_car() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket parkingTicket = new ParkingTicket();

        parkingLot.park(new Car(), parkingTicket);

        Assert.assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_fetch_car_with_parking_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkingTicket = new ParkingTicket();

        parkingLot.park(car, parkingTicket);
        Car fetchedCar = parkingLot.fetch(parkingTicket);

        Assert.assertEquals(car, fetchedCar);
    }

    @Test
    public void should_return_no_car_when_fetch_car_with_wrong_parking_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkingTicket = new ParkingTicket();

        parkingLot.park(car, parkingTicket);
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        Car fetchedCar = parkingLot.fetch(wrongParkingTicket);

        Assert.assertNull(fetchedCar);
    }

    @Test
    public void should_return_no_car_when_fetch_car_with_used_parking_ticket(){
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkingTicket = new ParkingTicket();

        parkingLot.park(car, parkingTicket);
        parkingLot.fetch(parkingTicket);
        Car fetchedCarInSecondTime = parkingLot.fetch(parkingTicket);

        Assert.assertNull(fetchedCarInSecondTime);
    }

}
