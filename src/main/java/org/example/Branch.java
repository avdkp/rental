package org.example;

import org.example.entities.Vehicle;
import org.example.entities.VehicleType;
import org.example.rules.CheapestVehicle;
import org.example.rules.Strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Branch {
    private Integer id;
    private String name;
    private List<Vehicle> vehicles = new ArrayList<>();

    private Strategy selectorStrategy;
    public Branch(String name, Map<Vehicle, Integer> vehicleCount, Strategy selectorStrategy) {
        this.name = name;
        this.selectorStrategy = selectorStrategy;
        for(Vehicle v:vehicleCount.keySet()) {
            for(int i=0;i<vehicleCount.get(v);++i) {
                Vehicle vehicle = new Vehicle(v.getType(), v.getHourlyRate());
                this.vehicles.add(vehicle);
            }
        }
    }

    public void setSelectorStrategy(Strategy selectorStrategy) {
        this.selectorStrategy = selectorStrategy;
    }

    public List<Vehicle> getAvailableVehicle(List<Integer> slots) {
        List<Vehicle> result = new ArrayList<>();
        for(Vehicle v:this.vehicles) {
            if(v.isAvailable(slots))
                result.add(v);
        }
        return result;
    }

    public void addVehicles(VehicleType vehicleType, float hourlyRate, Integer count) {
        for(int i=0;i<count;++i) {
            this.vehicles.add(new Vehicle(vehicleType, hourlyRate));
        }
    }

    public Vehicle getbestAvailableVehicle(VehicleType type, List<Integer> slots) {
        vehicles = this.vehicles.stream().filter(v->v.isAvailable(slots)).collect(Collectors.toList());
        return selectorStrategy.selectVehicle(this.vehicles, type);
    }
}
