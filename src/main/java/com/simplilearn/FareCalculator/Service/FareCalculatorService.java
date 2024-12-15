package com.simplilearn.FareCalculator.Service;

import com.simplilearn.FareCalculator.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Service
public class FareCalculatorService {
    private static final double RATE_PER_Hour= 15.0;

    @Autowired
    private RestTemplate restTemplate;



    public FareCalculatorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public double calculateFare(Long bookingId) {
        String url = "http://localhost:8080/bookings/" + bookingId;
        Booking booking;
        try {
            booking = restTemplate.getForObject(url, Booking.class);
            System.out.println("Received booking info: " + booking);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get booking info from Booking Service", e);
        }
        if (booking.getPickupTime() == null || booking.getDropoffTime() == null) {
            throw new IllegalArgumentException("Pickup time and dropoff time cannot be null");
        }

        // Calculate the duration between pickup and dropoff
        Duration duration = Duration.between(booking.getPickupTime(), booking.getDropoffTime());

        // Get the total minutes (only whole minutes count)
        long totalHours = duration.toHours();

        if (totalHours < 0) {
            throw new IllegalArgumentException("Dropoff time must be after pickup time");
        }

        // Calculate fare based on total minutes and rate
        double fare = totalHours * RATE_PER_Hour;

        System.out.println("Total Duration: " + totalHours + " minutes, Rate per minute: " + RATE_PER_Hour);
        System.out.println("Calculated Fare: " + fare);

        return fare;
    }
}
