package com.michaels.designhub.utils;

import java.util.List;
import java.util.stream.Collectors;

public class TrackingNumberValidator {
    // Method to validate a single tracking number
    public static boolean isValidTrackingNumber(String trackingNumber) {
        // Define the validation rule: alphanumeric and between 10 and 20 characters
        return trackingNumber != null && trackingNumber.matches("^[A-Za-z0-9]{10,20}$");
    }

    // Method to validate a list of tracking numbers
    public static List<String> validateTrackingNumbers(List<String> trackingNumbers) {
        // Filter out invalid tracking numbers
        return trackingNumbers.stream()
                .filter(trackingNumber -> !isValidTrackingNumber(trackingNumber))
                .collect(Collectors.toList());
    }

    public boolean isValidInput(String input) {
        // Check if input only contains alphanumeric characters
        return input != null && input.matches("^[A-Za-z0-9]+$");
    }
}
