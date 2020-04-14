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

    public ParkingTicket park(Car car) {
        if (car == null){
            return null;
        }

        if (isFull()) {
            return null;
        }

        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicketCarMap.put(parkingTicket, car);
        return parkingTicket;
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
