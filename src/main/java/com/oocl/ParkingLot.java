package com.oocl;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public static final int DEFAULT_CAPACITY_10 = 10;
    private Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<>();
    private int capacity;
    private int availableSpace;
    private float availablePositionRate;

    public ParkingLot() {
        this.capacity = DEFAULT_CAPACITY_10;
        this.availableSpace = DEFAULT_CAPACITY_10;
        this.availablePositionRate = 0f;
    }

    public ParkingLot(int parkingLotSize) {
        this.capacity = parkingLotSize;
        this.availableSpace = parkingLotSize;
        this.availablePositionRate = 0f;
    }

    public void park(Car car, ParkingTicket parkingTicket) {
        if (!isFull() && car != null) {
            parkingTicketCarMap.put(parkingTicket, car);
            availableSpace = capacity - parkingTicketCarMap.size();
            availablePositionRate =  (float)availableSpace / (float)capacity;
        }
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingTicketCarMap.remove(parkingTicket);
    }

    public boolean isFull() {
        return availableSpace == 0;
    }

    public int getAvailableSpace() {
        return availableSpace;
    }

    public float getAvailablePositionRate() {
        return availablePositionRate;
    }
}
