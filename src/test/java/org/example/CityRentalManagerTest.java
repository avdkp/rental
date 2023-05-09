package org.example;

import org.example.entities.Vehicle;
import org.example.entities.VehicleType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CityRentalManagerTest {

    @Test
    void addBranch() throws Exception {
        CityRentalManager cityRentalManager = new CityRentalManager();
        Map<Vehicle, Integer> mp = new HashMap<>();
        mp.put(new Vehicle(VehicleType.SUV, 10), 1);
//        mp.put(new Vehicle(VehicleType.SUV, 12), 1);
        mp.put(new Vehicle(VehicleType.BIKE, 5), 3);
        mp.put(new Vehicle(VehicleType.SEDAN, 120), 2);
        cityRentalManager.addBranch("ABC", mp);

        List<Vehicle> result = cityRentalManager.getAvailableVehicles(0, LocalDateTime.now(), LocalDateTime.of(2015,
                Month.JULY, 9, 19, 30, 40));


        mp = new HashMap<>();
        mp.put(new Vehicle(VehicleType.SUV, 8), 1);
        mp.put(new Vehicle(VehicleType.BIKE, 5), 1);
        mp.put(new Vehicle(VehicleType.SEDAN, 120), 1);
        cityRentalManager.addBranch("XYZ", mp);

        result = cityRentalManager.getAvailableVehicles(0, LocalDateTime.now(), LocalDateTime.of(2015,
                Month.JULY, 9, 19, 30, 40));


//        cityRentalManager.addVehicle(1, VehicleType.SUV, 50, 3);

        Vehicle v = cityRentalManager.rentVehicle(VehicleType.SUV, LocalDateTime.of(2024,
                Month.JULY, 9, 12, 30, 40), LocalDateTime.of(2024,
                Month.JULY, 12, 13, 30, 40));
        Vehicle v1 = cityRentalManager.rentVehicle(VehicleType.SUV, LocalDateTime.of(2024,
                Month.JULY, 9, 1, 30, 40), LocalDateTime.of(2024,
                Month.JULY, 12, 2, 30, 40));
//        Vehicle v2 = cityRentalManager.rentVehicle(VehicleType.BMW, LocalDateTime.of(2024,
//                Month.JULY, 9, 7, 30, 40), LocalDateTime.of(2024,
//                Month.JULY, 12, 19, 30, 40));
        List<Vehicle> vehicles = cityRentalManager.getAvailableVehicles(1, LocalDateTime.of(2024,
                Month.JULY, 9, 13, 30, 40), LocalDateTime.of(2024,
                Month.JULY, 12, 14, 30, 40));

        System.out.println(result);
    }
}