package com.oocl;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class SuperSmartParkingBoyTest {

    @Test
    public void should_park_car_in_parking_lot_with_more_space_when_park() {
        Car car = new Car();
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(3);
        ParkingTicket firstParkingTicket = new ParkingTicket(firstParkingLot);
        ParkingTicket secondParkingTicket = new ParkingTicket(secondParkingLot);
        firstParkingLot.park(car, firstParkingTicket);
        secondParkingLot.park(car, secondParkingTicket);

        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(Arrays.asList(firstParkingLot, secondParkingLot));
        ParkingTicket actualPackingTicket = superSmartParkingBoy.park(car);

        Assert.assertEquals(secondParkingLot, actualPackingTicket.getParkingLot());
    }
}
