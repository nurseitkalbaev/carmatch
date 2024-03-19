package org.nurseitkalbaev.carmatch.controller;

import org.nurseitkalbaev.carmatch.model.Car;
import org.nurseitkalbaev.carmatch.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/{carId}")
    public String getCarById(@PathVariable Long carId, Model model) {
        Car car = carService.getCarById(carId);
        if (car == null) {
            return "error";
        }
        model.addAttribute("car", car);
        return "carDetails";
    }


    @PostMapping
    public String createCar(@ModelAttribute Car car) {
        carService.createCar(car);
        return "/cars";
    }

    @PutMapping("/{carId}")
    public String updateCar(@PathVariable Long carId, @ModelAttribute Car updatedCar) {
        carService.updateCar(carId, updatedCar);
        return "/cars";
    }

    @DeleteMapping("/{carId}")
    public String deleteCar(@PathVariable Long carId) {
        carService.deleteCar(carId);
        return "/cars";
    }
}
