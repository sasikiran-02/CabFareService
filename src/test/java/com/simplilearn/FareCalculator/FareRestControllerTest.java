package com.simplilearn.FareCalculator;

import com.simplilearn.FareCalculator.RestContollers.FareRestController;
import com.simplilearn.FareCalculator.Service.FareCalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FareRestControllerTest {

    @Mock
    private FareCalculatorService fareCalculatorService;  // Mocked service

    @InjectMocks
    private FareRestController fareRestController;  // Controller under test

    @BeforeEach
    void setUp() {
        // Initialize the mocks before each test
    }

    @Test
    void testCalculateFare_Success() {
        // Given
        Long bookingId = 1L;
        double expectedFare = 25.0;
        when(fareCalculatorService.calculateFare(bookingId)).thenReturn(expectedFare);

        // When
        ResponseEntity<?> response = fareRestController.calculateFare(bookingId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Assert that the status is OK
        assertTrue(response.getBody().toString().contains("\"fare\": 25.0")); // Assert that the body contains the correct fare
    }

    @Test
    void testCalculateFare_BookingNotFound() {
        // Given
        Long bookingId = 999L;
        when(fareCalculatorService.calculateFare(bookingId)).thenThrow(new IllegalArgumentException("Booking not found"));

        // When
        ResponseEntity<?> response = fareRestController.calculateFare(bookingId);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode()); // Assert that the status is BAD_REQUEST
        assertTrue(response.getBody().toString().contains("\"error\": \"Booking not found\"")); // Assert that the error message is correct
    }

    @Test
    void testCalculateFare_InternalError() {
        // Given
        Long bookingId = 1L;
        when(fareCalculatorService.calculateFare(bookingId)).thenThrow(new RuntimeException("Unexpected error"));

        // When
        ResponseEntity<?> response = fareRestController.calculateFare(bookingId);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode()); // Assert that the status is INTERNAL_SERVER_ERROR
        assertTrue(response.getBody().toString().contains("\"error\": \"An unexpected error occurred\"")); // Assert that the error message is correct
    }
}
