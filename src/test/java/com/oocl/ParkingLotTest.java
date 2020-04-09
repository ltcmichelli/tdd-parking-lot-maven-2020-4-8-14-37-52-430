package com.oocl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {
    private ParkingLot parkingLot;
    private Car car;

    @Before
    public void setup(){
        parkingLot = new ParkingLot();
        car = new Car();
    }

    @Test
    public void should_return_parking_ticket_when_park_car() {
        ParkingTicket parkingTicket = new ParkingTicket(parkingLot);

        parkingLot.park(car, parkingTicket);

        Assert.assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_fetch_car_with_parking_ticket() {
        ParkingTicket parkingTicket = new ParkingTicket(parkingLot);

        parkingLot.park(car, parkingTicket);
        Car fetchedCar = parkingLot.fetch(parkingTicket);

        Assert.assertEquals(car, fetchedCar);
    }

    @Test
    public void should_return_no_car_when_fetch_car_with_wrong_parking_ticket() {
        ParkingLot secondParkingLot = new ParkingLot();
        ParkingTicket parkingTicket = new ParkingTicket(parkingLot);

        parkingLot.park(car, parkingTicket);
        ParkingTicket wrongParkingTicket = new ParkingTicket(secondParkingLot);
        Car fetchedCar = parkingLot.fetch(wrongParkingTicket);

        Assert.assertNull(fetchedCar);
    }

    @Test
    public void should_return_no_car_when_fetch_car_with_used_parking_ticket() {
        ParkingTicket parkingTicket = new ParkingTicket(parkingLot);

        parkingLot.park(car, parkingTicket);
        parkingLot.fetch(parkingTicket);
        Car fetchedCarInSecondTime = parkingLot.fetch(parkingTicket);

        Assert.assertNull(fetchedCarInSecondTime);
    }

}
