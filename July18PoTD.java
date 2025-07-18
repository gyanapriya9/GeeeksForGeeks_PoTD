// PoTD Date: 18-07-2025
// Question: LCM Triplet

/*
 * Flashcard Summary:
 * -------------------
 * Task: Find the maximum LCM of any three numbers <= n.
 * Constraint: 1 ≤ n ≤ 1000
 * Approach:
 *  - Try to choose 3 numbers close to n which are co-prime (LCM will be high).
 *  - For even n, some combinations (like those divisible by 3) reduce LCM.
 *  - Use cases:
 *     a) If n is odd: just use n * (n-1) * (n-2)
 *     b) If n is even and not divisible by 3: use n * (n-1) * (n-3)
 *     c) If n is even and divisible by 3: use (n-1) * (n-2) * (n-3)
 */

class Solution {
    int lcmTriplets(int n) {
        int ans;

        // Case 1: For n less than 3, return n (e.g., n=1 or 2)
        if (n < 3) {
            ans = n;
        }
        // Case 2: If n is odd, then use (n, n-1, n-2)
        else if (n % 2 != 0) {
            ans = n * (n - 1) * (n - 2);
        }
        // Case 3: If n is even and not divisible by 3, use (n, n-1, n-3)
        else if (n % 3 != 0) {
            ans = n * (n - 1) * (n - 3);
        }
        // Case 4: If n is even and divisible by 3, use (n-1, n-2, n-3)
        else {
            ans = (n - 1) * (n - 2) * (n - 3);
        }

        return ans;
    }
}

/*
 * Time Complexity: O(1) — only a few arithmetic operations.
 * Space Complexity: O(1) — constant extra space.
 */
