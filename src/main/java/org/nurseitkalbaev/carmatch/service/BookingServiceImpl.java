package org.nurseitkalbaev.carmatch.service;

import org.nurseitkalbaev.carmatch.model.Booking;
import org.nurseitkalbaev.carmatch.model.Car;
import org.nurseitkalbaev.carmatch.model.User;
import org.nurseitkalbaev.carmatch.repository.BookingRepository;
import org.nurseitkalbaev.carmatch.repository.CarRepository;
import org.nurseitkalbaev.carmatch.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public Booking createBooking(Booking newBooking) {
        return bookingRepository.save(newBooking);
    }

    public Booking updateBooking(Long id, Booking updatedBooking) {
        if (updatedBooking == null) {
            return null;
        }

        Booking booking = bookingRepository.findById(id).orElse(null);


        if (booking != null) {
            BeanUtils.copyProperties(updatedBooking, booking, "id");

            return bookingRepository.save(booking);
        } else {
            return null;
        }
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public Booking bookCar(Long userId, Long carId, Date startDate, Date endDate) {
        User user = userRepository.findById(userId).orElse(null);
        Car car = carRepository.findById(carId).orElse(null);

        if (user == null || car == null) {
            return null; // User or car not found
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setCar(car);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);

        return bookingRepository.save(booking);
    }

    @Override
    public void cancelBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    @Override
    public List<Booking> getBookingsForUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return Collections.emptyList(); // User not found
        }
        return bookingRepository.findByUser(user);
    }


}
