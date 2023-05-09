package org.example.entities;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;

public class Vehicle {
    private final UUID uuid;
    private VehicleType type;
    private float hourlyRate;
    private Map<Integer, Boolean> availableSlots;

    public Vehicle(VehicleType type, float hourlyRate) {
        this.uuid = UUID.randomUUID();
        this.type = type;
        this.hourlyRate = hourlyRate;
        this.availableSlots = new HashMap<>();
        for(int i = 0; i<24; ++i) {
            this.availableSlots.put(i, true);
        }
    }

    public VehicleType getType() {
        return type;
    }

    public float getHourlyRate() {
        return hourlyRate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void rent(List<Integer> slots) {
        for(int s:slots) {
            this.availableSlots.put(s, false);
        }
    }

    public boolean isAvailable(List<Integer> slots) {
        Boolean result = true;
        for(Integer i:slots) {
            result &= this.availableSlots.get(i);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return uuid.equals(vehicle.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
