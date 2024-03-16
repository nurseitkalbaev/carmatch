package org.nurseitkalbaev.carmatch.service;

import org.nurseitkalbaev.carmatch.model.Booking;
import org.nurseitkalbaev.carmatch.repository.BookingRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

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
}
