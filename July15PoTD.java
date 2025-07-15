// PoTD 15-07-2025

// Flashcard Summary:
// You're given a number in string format (can be very large).
// Task: Check whether the number is divisible by 13 without converting the whole number into an integer (to avoid overflow).

// Approach:
// We use the modulo property: ((a * 10) + b) % m = ((a % m) * 10 + b) % m
// So, we build the remainder step-by-step for each digit and check if remainder % 13 == 0

class Solution {
    public boolean divby13(String s) {
        // Initialize remainder
        int remainder = 0;

        // Process each digit of the string from left to right
        for (int i = 0; i < s.length(); i++) {
            // Convert character to integer
            int digit = s.charAt(i) - '0';

            // Update the remainder as per modulo 13 rule
            // (remainder * 10 + digit) % 13 gives the updated remainder
            remainder = (remainder * 10 + digit) % 13;
        }

        // If remainder is 0, number is divisible by 13
        return remainder == 0;
    }
}
