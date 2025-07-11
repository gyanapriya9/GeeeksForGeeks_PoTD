// PoTD Date: 11 July 2025
// Problem: Trail of Ones
// Difficulty: Medium
// Description:
// Given an integer n, count how many binary strings of length n have at least one pair of consecutive 1s.
// A binary string contains only 0s and 1s.

class Solution {
    public int countConsec(int n) {
        // The problem is to count the number of binary strings of length n that contain at least one "11" pair.

        // Total binary strings of length n = 2^n
        // Instead of directly generating all strings, we use a dynamic approach to count efficiently.

        // Approach:
        // We'll count how many strings contain at least one pair of consecutive 1s using bottom-up logic.
        // We define two variables:
        // - prev0: number of strings ending in 0 (no consecutive 1s formed yet)
        // - prev1: number of strings ending in 1 and already formed at least one "11"

        // Initially, no strings built yet, so both are zero.
        int prev0 = 0, prev1 = 0;

        // We work from position n to 1
        // At each position, we update our state:
        // - curr0: If last added bit is 0, we can add both 0 and 1 (extend prev0 and prev1)
        // - curr1: If we now add a second 1 (after a previous 1), we form a "11" and 
        //          all remaining positions can be anything => 2^(n - i) combinations
        for (int i = n; i >= 1; i--) {
            // Total valid strings that can be built if current bit is 0
            int curr0 = prev0 + prev1;

            // If we now place another 1 (after one 1 already), we've formed "11"
            // So, add all combinations for remaining (n - i) bits
            int curr1 = prev0 + (1 << (n - i)); // (1 << x) is same as 2^x

            // Update for the next iteration
            prev0 = curr0;
            prev1 = curr1;
        }

        // At the end, prev0 contains the total number of valid strings of length n
        // that contain at least one pair of consecutive 1s.
        return prev0;
    }
}
