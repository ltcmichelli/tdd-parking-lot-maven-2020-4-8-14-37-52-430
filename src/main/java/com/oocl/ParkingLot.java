package com.oocl;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public static final int DEFAULT_CAPACITY_10 = 10;
    private Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<>();
    private int capacity;
    private int space;

    public ParkingLot() {
        this.capacity = DEFAULT_CAPACITY_10;
        this.space = DEFAULT_CAPACITY_10;
    }

    public ParkingLot(int parkingLotSize) {
        this.capacity = parkingLotSize;
        this.space = parkingLotSize;
    }

    public void park(Car car, ParkingTicket parkingTicket) {
        if (!isFull() && car != null) {
            parkingTicketCarMap.put(parkingTicket, car);
            space = capacity - parkingTicketCarMap.size();
        }
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingTicketCarMap.remove(parkingTicket);
    }

    public boolean isFull() {
        return space == 0;
    }

    public int getSpace() {
        return space;
    }
}
