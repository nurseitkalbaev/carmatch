package org.nurseitkalbaev.carmatch.service;

import org.nurseitkalbaev.carmatch.model.Booking;

import java.util.Date;
import java.util.List;

public interface BookingService {
    List<Booking> getAllBookings();
    Booking getBookingById(Long id);
    Booking createBooking(Booking newBooking);
    Booking updateBooking(Long id, Booking updatedBooking);
    void deleteBooking(Long id);
    Booking bookCar(Long userId, Long carId, Date startDate, Date endDate);
    void cancelBooking(Long bookingId);
    List<Booking> getBookingsForUser(Long userId);


}