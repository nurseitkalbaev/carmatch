package org.nurseitkalbaev.carmatch.model;

import jakarta.persistence.*;
import lombok.Data;

// Car class representing a car entity in the system
@Entity
@Data
public class Car {
    // Primary key for the car entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Make of the car (e.g., Toyota, Honda, etc.)
    private String make;

    // Model of the car (e.g., Camry, Accord, etc.)
    private String model;
    private int year;

    // Price of the car to rent or exchange
    private double price;

    //Location of the car where user can pick up a car
    private String location;
    private String description;

    //Image of the car
    private String imageUrl;

    @ManyToOne
    private User owner;
}