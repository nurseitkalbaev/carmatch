package org.nurseitkalbaev.carmatch.controller;

import org.nurseitkalbaev.carmatch.model.Booking;
import org.nurseitkalbaev.carmatch.model.BookingRequest;
import org.nurseitkalbaev.carmatch.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public String bookCar(@ModelAttribute BookingRequest request, Model model) {
        Booking booking = bookingService.bookCar(request.getUserId(), request.getCarId(), request.getStartDate(), request.getEndDate());
        if (booking == null) {
            return "error";
        }
        model.addAttribute("booking", booking);
        return "bookingConfirmation";
    }

    @DeleteMapping("/{bookingId}")
    public String cancelBooking(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
        return "/bookings";
    }

    @GetMapping("/user/{userId}")
    public String getBookingsForUser(@PathVariable Long userId, Model model) {
        List<Booking> bookings = bookingService.getBookingsForUser(userId);
        model.addAttribute("bookings", bookings);
        return "userBookings";
    }
}
