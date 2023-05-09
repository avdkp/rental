package org.example.rules;

import org.example.entities.Vehicle;
import org.example.entities.VehicleType;

import java.util.List;

public interface Strategy {
    Vehicle selectVehicle(List<Vehicle> vehicles, VehicleType type);
}
