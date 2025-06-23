// 2025-06-23 PoTD 
// Problem Summary (Layman Explanation):
// You're given a bunch of **digits** (not full numbers).
// Your goal is to create two numbers using **all digits**, such that their **sum is as small as possible**.
// Return that **minimum possible sum** as a string (no extra zeros in the beginning).

import java.util.*;

class Solution {

    // Helper method to add two large numbers (represented as Strings)
    public String addStrings(String num1, String num2) {
        // If needed, swap to make sure num1 is shorter
        if (num1.length() > num2.length()) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }

        StringBuilder sum = new StringBuilder();
        int carry = 0;

        int n1 = num1.length(), n2 = num2.length();
        int diff = n2 - n1;

        // Start from the end of both strings
        for (int i = n1 - 1; i >= 0; i--) {
            int digit1 = num1.charAt(i) - '0';
            int digit2 = num2.charAt(i + diff) - '0';
            int total = digit1 + digit2 + carry;

            sum.append(total % 10);   // take last digit
            carry = total / 10;       // keep carry
        }

        // Process remaining digits in longer number
        for (int i = diff - 1; i >= 0; i--) {
            int digit = num2.charAt(i) - '0';
            int total = digit + carry;
            sum.append(total % 10);
            carry = total / 10;
        }

        // If carry remains, add it
        if (carry != 0) {
            sum.append(carry);
        }

        // Final sum needs to be reversed
        return sum.reverse().toString();
    }

    // Main logic to form two numbers and return their minimum sum
    public String minSum(int[] arr) {
        Arrays.sort(arr);  // Sort digits from smallest to largest

        StringBuilder num1 = new StringBuilder();
        StringBuilder num2 = new StringBuilder();

        // Distribute digits alternately into num1 and num2
        // This helps to keep both numbers roughly equal in size and low value
        for (int i = 0; i < arr.length; i++) {
            if ((i % 2) == 0) {
                num1.append(arr[i]);
            } else {
                num2.append(arr[i]);
            }
        }

        // Add both numbers and return the result
        return addStrings(num1.toString(), num2.toString());
    }
}
