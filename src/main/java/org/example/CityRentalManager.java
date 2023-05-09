package org.example;

import org.example.rules.CheapestVehicle;
import org.example.rules.Strategy;
import org.example.util.Slot;
import org.example.entities.Vehicle;
import org.example.entities.VehicleType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CityRentalManager {

    private final List<Branch> branches = new ArrayList<>();
    private Strategy selectorStrategy = new CheapestVehicle();

    public int addBranch(String branch, Map<Vehicle, Integer> vehicleCount) {
        this.branches.add(new Branch(branch, vehicleCount, selectorStrategy));
        return branches.size()-1;
    }

    public void addVehicle(Integer branchId, VehicleType vehicleType, float hourlyRate, Integer count) {
        this.branches.get(branchId).addVehicles(vehicleType, hourlyRate, count);
    }

    public List<Vehicle> getAvailableVehicles(Integer branchId, LocalDateTime startTime, LocalDateTime endTime) {
        List<Vehicle> result = new ArrayList<>();
        List<Integer> slots = Slot.getSlots(startTime, endTime);
        return this.branches.get(branchId).getAvailableVehicle(slots);
    }

    public Vehicle rentVehicle(VehicleType type, LocalDateTime startTime, LocalDateTime endTime) throws Exception {
        if(startTime.isBefore(LocalDateTime.now())) {
            throw new Exception("Time can not be in past");
        }
        if(endTime.isBefore(startTime)) {
            throw new Exception("end Time can not be in past");
        }

        List<Integer> slots = Slot.getSlots(startTime, endTime);
        List<Vehicle> vehicles = new ArrayList<>();
        for(Branch b:this.branches) {
            vehicles.add(b.getbestAvailableVehicle(type, slots));
        }
        Vehicle vehicle = selectorStrategy.selectVehicle(vehicles, type);
        vehicle.rent(slots);
        return vehicle;
    }
}
