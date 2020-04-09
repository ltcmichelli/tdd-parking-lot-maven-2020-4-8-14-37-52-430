package com.oocl;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ParkingBoyTest {
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
    public void should_park_car_to_parking_lot(){
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        ParkingTicket parkingTicket = parkingBoy.park(car);
        Car fetchedCarFromParkingLot = parkingLot.fetch(parkingTicket);

        Assert.assertEquals(fetchedCarFromParkingLot, car);
    }

    @Test
    public void should_fetch_car_from_parking_lot(){
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
}
