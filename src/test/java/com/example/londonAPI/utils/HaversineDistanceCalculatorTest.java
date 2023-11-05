package com.example.londonAPI.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HaversineDistanceCalculatorTest {

    @Test
    public void testCalculateDistance() {
        double lat1 = 51.5074; // London latitude
        double lon1 = -0.1278; // London longitude
        double lat2 = 48.8566;
        double lon2 = 2.3522;

        // Expected distance in miles (approximately)
        double expectedDistance = 213.47;

        // Calculate the distance
        double calculatedDistance = HaversineDistanceCalculator.calculateDistance(lat1, lon1, lat2, lon2);

        // Define a tolerance for the test (allowing for slight differences due to precision)
        double tolerance = 0.01; // You can adjust this as needed

        // Assert that the calculated distance is within the tolerance of the expected distance
        assertEquals(expectedDistance, calculatedDistance, tolerance);
    }
}