package com.oocl;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class SmartParkingBoyTest {

    @Test
    public void should_park_car_in_parking_lot_with_more_space_when_park() {
        Car car = new Car();
        ParkingLot lessSpaceParkingLot = new ParkingLot(2);
        ParkingLot moreSpaceParkingLot = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(lessSpaceParkingLot, moreSpaceParkingLot));

        ParkingTicket actualPackingTicket = smartParkingBoy.park(car);

        Assert.assertEquals(moreSpaceParkingLot, actualPackingTicket.getParkingLot());
    }
}
