package com.oocl;

import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTest {

    @Test
    public void should_return_parking_ticket_when_parking_boy_park_car(){
        ParkingLot parkingLot = new ParkingLot();

        ParkingTicket parkingTicket = parkingLot.park(new Car());

        Assert.assertNotNull(parkingTicket);
    }
}
