package com.simplilearn.FareCalculator.RestContollers;

import com.simplilearn.FareCalculator.Service.FareCalculatorService;
import com.simplilearn.FareCalculator.model.Booking;
import com.simplilearn.FareCalculator.Service.FareCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class FareRestController {

    private final FareCalculatorService fareService;

    @Autowired
    public FareRestController(FareCalculatorService fareService) {
        this.fareService = fareService;
    }

    /**
     * Endpoint to calculate fare based on booking details.
     *
     * @param booking The booking information from the request body.
     * @return ResponseEntity with the fare amount.
     */
    @GetMapping("/calculate/{bookingId}")
    public ResponseEntity<?> calculateFare(@PathVariable Long bookingId) {

        try {
            double fare = fareService.calculateFare(bookingId);
            return ResponseEntity.ok().body("{ \"fare\": " + fare + " }");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"error\": \"" + e.getMessage() + "\" }");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{ \"error\": \"An unexpected error occurred\" }");
        }
    }
}
