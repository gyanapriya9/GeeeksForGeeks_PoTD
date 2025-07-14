// PoTD 14 jUly 2025


// Flashcard:
// We are given a binary string. We need to cut it into the *minimum number* of substrings such that:
// 1. Each substring represents a power of 5 (in decimal).
// 2. No substring should have leading zeros.
// If this is not possible, return -1.

// Approach: Dynamic Programming (DP) + Precomputation
// - Precompute powers of 5 that can fit into a 32-bit integer.
// - Use bottom-up DP from right to left.
// - At every index, try to extend the substring up to the end, and if it's a valid power of 5 and doesn't have leading zero, consider that cut.

class Solution {
    public int cuts(String s) {
        // If the first character is '0', then it's not valid to start with a power of 5
        if (s.charAt(0) == '0') return -1;

        int n = s.length();
        int INF = n + 1;  // A large number representing impossible state

        // Step 1: Precompute powers of 5 (in decimal) and store in a Set for quick lookup
        Set<Integer> powersOf5 = new HashSet<>();
        int val = 1;
        while (val <= 1_000_000_000) {
            powersOf5.add(val);
            val *= 5;
        }

        // Step 2: Create a DP array where dp[i] = min cuts needed from index i to end
        int[] dp = new int[n + 1];
        Arrays.fill(dp, INF);

        // Base case: empty suffix requires 0 cuts
        dp[n] = 0;

        // Step 3: Traverse from the end of the string to the beginning
        for (int i = n - 1; i >= 0; --i) {

            // Skip if current starting digit is '0' (invalid due to leading zeros)
            if (s.charAt(i) == '0') continue;

            int num = 0;

            // Try forming all substrings starting from index i to j
            for (int j = i; j < n; ++j) {
                // Convert binary string to decimal value
                num = num * 2 + (s.charAt(j) == '1' ? 1 : 0);

                // Check if the number is a power of 5 and we can make a valid cut at position j+1
                if (powersOf5.contains(num)) {
                    if (dp[j + 1] != INF) {
                        dp[i] = Math.min(dp[i], 1 + dp[j + 1]);
                    }
                }
            }
        }

        // If we couldn't form a valid split starting from 0, return -1
        return dp[0] >= INF ? -1 : dp[0];
    }
}
