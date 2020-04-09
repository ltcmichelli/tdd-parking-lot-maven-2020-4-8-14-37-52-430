package com.oocl;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class ParkingLotTest {
    private Car car = new Car();
    private ParkingTicket parkingTicket = new ParkingTicket();

    private HashMap<ParkingTicket, Car> fullParkingMap = new HashMap<ParkingTicket, Car>() {{
        put(parkingTicket, car);
        put(parkingTicket, car);
        put(parkingTicket, car);
        put(parkingTicket, car);
        put(parkingTicket, car);
        put(parkingTicket, car);
        put(parkingTicket, car);
        put(parkingTicket, car);
        put(parkingTicket, car);
        put(parkingTicket, car);
    }};

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

//    @Test
//    public void should_not_park_car_when_parking_lot_capacity_is_full(){
//        ParkingLot parkingLot = new ParkingLot();
//        parkingLot.setParkingTicketCarMap(fullParkingMap);
//        Car car = new Car();
//        ParkingTicket parkingTicket = new ParkingTicket();
//
//        parkingLot.park(car, parkingTicket);
//        Assert.assertNull(parkingTicket);
//
//    }
}
