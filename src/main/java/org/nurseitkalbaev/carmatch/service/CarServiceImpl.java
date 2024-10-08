package org.nurseitkalbaev.carmatch.service;

import org.nurseitkalbaev.carmatch.model.Car;
import org.nurseitkalbaev.carmatch.repository.CarRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public List<Car> getCarsOwnedByUser(Long ownerId) {
        return carRepository.findByOwnerId(ownerId);
    }
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    public Car createCar(Car newCar){
        return carRepository.save(newCar);
    }
    public void deleteCar(Long id){
        if(!carRepository.existsById(id)){
            return;
        }
        carRepository.deleteById(id);
    }
    public Car updateCar(Long id, Car updatedCar){
        if (updatedCar == null) {
            return null;
        }

        Car car = carRepository.findById(id).orElse(null);

        if (car != null) {
            BeanUtils.copyProperties(updatedCar, car, "id");

            return carRepository.save(car);
        } else {
            return null;
        }
    }

}
