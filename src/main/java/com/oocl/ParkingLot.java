package com.oocl;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<ParkingTicket, Car>();
    private int capacity = 0;

    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        if (capacity < 10 && car != null){
            parkingTicketCarMap.put(parkingTicket, car);
            capacity += 1;
            return parkingTicket;
        }
        return null;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        Car car = parkingTicketCarMap.remove(parkingTicket);
        return car;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
