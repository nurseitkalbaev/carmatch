package org.nurseitkalbaev.carmatch.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String make;
    private String model;
    private int year;
    private double price;
    private String location;
    private String description;
    private String imageUrl;

    @ManyToOne
    private User owner;
}