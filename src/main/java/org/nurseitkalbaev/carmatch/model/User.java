package org.nurseitkalbaev.carmatch.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String profilePictureUrl;

    @OneToMany(mappedBy = "owner" , cascade = CascadeType.ALL)
    private List<Car> cars;

    @OneToMany(mappedBy = "booker", cascade = CascadeType.ALL)
    private List<Booking> bookings;

}
