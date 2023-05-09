package org.example.rules;

import org.example.entities.Vehicle;
import org.example.entities.VehicleType;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CheapestVehicle implements Strategy {

    @Override
    public Vehicle selectVehicle(List<Vehicle> vehicles, VehicleType vehicleType) {
        Stream<Vehicle> filteredVehicle = vehicles.stream().filter(v -> v.getType() == vehicleType);
        List<Vehicle> data = filteredVehicle.sorted(new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                return Float.compare(o1.getHourlyRate(), o2.getHourlyRate());
            }
        }).collect(Collectors.toList());
        return data.isEmpty() ? null : data.get(0);
    }
}
