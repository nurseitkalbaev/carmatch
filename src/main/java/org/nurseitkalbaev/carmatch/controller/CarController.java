package org.nurseitkalbaev.carmatch.controller;

import jakarta.servlet.http.HttpSession;
import org.nurseitkalbaev.carmatch.model.Car;
import org.nurseitkalbaev.carmatch.model.User;
import org.nurseitkalbaev.carmatch.service.CarService;
import org.nurseitkalbaev.carmatch.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vehicles")
public class CarController {

    private CarService carService;
    private UserService userService;

    public CarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/all")
    public String getAllCars(Model model) {
        List<Car> allCars = carService.getAllCars(); // Assuming there's a method in CarService to retrieve all cars
        model.addAttribute("allCars", allCars);
        return "allCars";
    }

    @GetMapping("/listings")
    public String getUserCars(Model model , HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if(currentUser != null){
            List<Car> listings = carService.getCarsOwnedByUser(currentUser.getId());
            model.addAttribute("listings", listings);
            return "vehicles";
        }
        else{
            return "redirect:/login";
        }

    }


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
        return "carRental";
    }

    @PutMapping("/{carId}")
    public String updateCar(@PathVariable Long carId, @ModelAttribute Car updatedCar) {
        carService.updateCar(carId, updatedCar);
        return "carRental";
    }

    @DeleteMapping("/{carId}")
    public String deleteCar(@PathVariable Long carId) {
        carService.deleteCar(carId);
        return "carRental";
    }
}
