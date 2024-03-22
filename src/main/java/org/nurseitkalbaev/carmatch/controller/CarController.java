package org.nurseitkalbaev.carmatch.controller;

import jakarta.servlet.http.HttpSession;
import org.nurseitkalbaev.carmatch.model.Car;
import org.nurseitkalbaev.carmatch.model.User;
import org.nurseitkalbaev.carmatch.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vehicles")
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/carRental")
    public String getAllCars(Model model, HttpSession session) {
        boolean isAuthenticated = session.getAttribute("user") != null;
        model.addAttribute("authenticated", isAuthenticated);
        List<Car> allCars = carService.getAllCars();
        model.addAttribute("allCars", allCars);
        return "carRental";
    }

    @GetMapping("/listings")
    public String getUserCars(Model model , HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        boolean isAuthenticated = session.getAttribute("user") != null;
        model.addAttribute("authenticated", isAuthenticated);
        if(currentUser != null){
            List<Car> listings = carService.getCarsOwnedByUser(currentUser.getId());
            model.addAttribute("cars", listings);
            return "vehicles";
        }
        else{
            return "redirect:/login";
        }

    }


    @GetMapping("/carDetails/{carId}")
    public String getCarById(@PathVariable Long carId, Model model, HttpSession session) {
        boolean isAuthenticated = session.getAttribute("user") != null;
        model.addAttribute("authenticated", isAuthenticated);
        Car car = carService.getCarById(carId);
        if (car == null) {
            return "error";
        }
        model.addAttribute("car", car);
        return "carDetails";
    }


    @PostMapping("/add")
    public String addCar(@ModelAttribute Car newCar, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        newCar.setOwner(currentUser);
        carService.createCar(newCar);
        return "redirect:/vehicles/listings";
    }
    @GetMapping("/editVehicle/{carId}")
    public String editVehicle(@PathVariable Long carId, Model model, HttpSession session) {
        boolean isAuthenticated = session.getAttribute("user") != null;
        model.addAttribute("authenticated", isAuthenticated);
        Car car = carService.getCarById(carId);
        if (car == null) {
            return "error";
        }
        model.addAttribute("car", car);
        return "editVehicle";
    }

    @PostMapping("/editVehicle/{carId}")
    public String updateCar(@PathVariable Long carId, @ModelAttribute Car updatedCar) {
        updatedCar.setId(carId);
        carService.updateCar(carId, updatedCar);
        return "redirect:/vehicles/listings";
    }

    @PostMapping("/delete")
    public String deleteCar(@RequestParam("carId") Long carId) {
        carService.deleteCar(carId);
        return "redirect:/vehicles/listings";
    }
}
