package org.nurseitkalbaev.carmatch.service;

import org.nurseitkalbaev.carmatch.model.Car;
import java.util.List;

// CarService interface for managing car-related operations
public interface CarService {
    // Method to get all cars
    List<Car> getAllCars();
    List<Car> getCarsOwnedByUser(Long ownerId);
    Car getCarById(Long id);
    Car createCar(Car newCar);
    Car updateCar(Long id, Car updatedCar);
    void deleteCar(Long id);
}