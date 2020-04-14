package com.oocl;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class SuperSmartParkingBoyTest {

    @Test
    public void should_park_car_in_parking_lot_with_more_larger_available_ratio_when_park() {
        Car car = new Car();
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(3);
        ParkingTicket firstParkingTicket =firstParkingLot.park(car);;
        ParkingTicket secondParkingTicket = secondParkingLot.park(car);

        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(Arrays.asList(firstParkingLot, secondParkingLot));
        ParkingTicket actualPackingTicket = superSmartParkingBoy.park(car);

        Assert.assertEquals(secondParkingLot, actualPackingTicket.getParkingLot());
    }

    @Test
    public void should_park_car_in_first_parking_lot_with_equal_available_ratio_when_park() {
        Car car = new Car();
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(2);
        ParkingTicket firstParkingTicket = firstParkingLot.park(car);
        ParkingTicket secondParkingTicket = secondParkingLot.park(car);

        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(Arrays.asList(firstParkingLot, secondParkingLot));
        ParkingTicket actualPackingTicket = superSmartParkingBoy.park(car);

        Assert.assertEquals(firstParkingLot, actualPackingTicket.getParkingLot());
    }
}
