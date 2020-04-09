package com.oocl;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public static final int DEFAULT_CAPACITY_10 = 10;
    private Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<ParkingTicket, Car>();
    private int capacity;

    public ParkingLot() {
        this.capacity = DEFAULT_CAPACITY_10;
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public void park(Car car, ParkingTicket parkingTicket) {
        if (!isFull() && car != null) {
            parkingTicketCarMap.put(parkingTicket, car);
        }
    }

    public Car fetch(ParkingTicket parkingTicket) {
        Car car = parkingTicketCarMap.remove(parkingTicket);
        return car;
    }

    public boolean isFull() {
        return parkingTicketCarMap.size() == capacity;
    }
}
