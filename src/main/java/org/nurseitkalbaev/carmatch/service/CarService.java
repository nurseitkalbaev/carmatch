package org.nurseitkalbaev.carmatch.service;

import org.nurseitkalbaev.carmatch.model.Car;
import java.util.List;

public interface CarService {
    List<Car> getAllCars();
    List<Car> getCarsOwnedByUser(Long ownerId);
    Car getCarById(Long id);
    Car createCar(Car newCar);
    Car updateCar(Long id, Car updatedCar);
    void deleteCar(Long id);
}