package com.oocl;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public static final int DEFAULT_CAPACITY_10 = 10;
    private Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<>();
    private int capacity;

    public ParkingLot() {
        this.capacity = DEFAULT_CAPACITY_10;
    }

    public ParkingLot(int parkingLotSize) {
        this.capacity = parkingLotSize;
    }

    public void park(Car car, ParkingTicket parkingTicket) {
        if (!isFull() && car != null) {
            parkingTicketCarMap.put(parkingTicket, car);
        }
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingTicketCarMap.remove(parkingTicket);
    }

    public boolean isFull() {
        return capacity - parkingTicketCarMap.size() == 0;
    }

    public int getAvailableSpace() {
        return capacity - parkingTicketCarMap.size();
    }

    public float getAvailablePositionRate() {
        return (float) getAvailableSpace() / capacity;
    }
}
