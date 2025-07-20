import java.util.*;

// ✅ PoTD Date: 20th July 2025
/*
🔹 Problem Summary (Flashcard Format):

Given:
- Integer n = number of digits in the numbers to be formed
- Array arr[] = list of digits (0 to 9)

Goal:
- Count how many *n-digit* positive integers can be formed such that
  *at least one digit* from arr[] appears in the number.

Example:
n = 2, arr = [3, 5] ➝ Output: 34

Approach:
- Total n-digit numbers = 9 * 10^(n-1)
- Calculate count of n-digit numbers that do *not* use any digit from arr[]
- Subtract this from total to get numbers that include *at least one* digit from arr[]
*/

class Solution {

    // Fast exponentiation to compute base^exp efficiently
    static int fastPow(int base, int exp) {
        int result = 1;
        while (exp > 0) {
            if ((exp & 1) != 0) result *= base;  // If the bit is 1, multiply the result
            base *= base;                        // Square the base each time
            exp >>= 1;                           // Right shift exponent (divide by 2)
        }
        return result;
    }

    static int countValid(int n, int[] arr) {
        
        // Step 1: Mark "good digits" i.e., digits we want to be present in the number
        boolean[] good = new boolean[10];
        for (int d : arr) good[d] = true;

        // Step 2: Count how many digits are NOT in arr[]:
        // f = count of forbidden digits (0-9 not in arr)
        // f0 = count of forbidden digits excluding '0' (for first digit)
        int f = 0, f0 = 0;
        for (int d = 0; d < 10; d++) {
            if (!good[d]) {
                f++;                      // total forbidden digits
                if (d != 0) f0++;         // forbidden digits allowed in first position (non-zero)
            }
        }

        // Step 3: Total number of n-digit numbers = 9 * 10^(n - 1)
        // First digit can't be 0, so we have 9 options there, and 10 options for the rest
        int total = 9 * fastPow(10, n - 1);
        
        // Step 4: Count of numbers with NO digit from arr[]
        // These are formed entirely from forbidden digits
        // First digit: f0 options (excluding 0)
        // Remaining digits: f options each
        int noneAllowed = (n == 1) ? f0 : f0 * fastPow(f, n - 1);

        // Step 5: Valid count = total - numbers with no good digit
        return total - noneAllowed;
    }

    public static void main(String[] args) {
        int n = 2;
        int[] arr = {3, 5};

        // Output should be 34
        System.out.println(countValid(n, arr));
    }
}

/*
🔹 Time Complexity:
- O(log n) for fastPow
- O(1) overall since n ≤ 9, and digit array is max 10 elements

🔹 Space Complexity:
- O(1) — uses constant extra space (boolean array of size 10)
*/
