package com.oocl;

import org.junit.Assert;
import org.junit.Test;

public class ParkingBoyTest {
    @Test
    public void should_park_car_to_parking_lot(){
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        ParkingTicket parkingTicket = parkingBoy.park(car);
        Car fetchedCarFromParkingLot = parkingLot.fetch(parkingTicket);

        Assert.assertEquals(fetchedCarFromParkingLot, car);
    }

}
