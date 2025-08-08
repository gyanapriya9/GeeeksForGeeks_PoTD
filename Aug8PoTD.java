/*
    Today Date PoTD: 8th Aug 2025

    Quick Flashcard:
    Problem:
    - Given a lowercase string, find the length of the longest proper prefix
      which is also a suffix.
    - Proper prefix ≠ entire string, but it can overlap with suffix.

    Example:
    s = "abab" → Answer = 2 ("ab")

    Approach:
    - Use **Double Hashing** to compare prefixes and suffixes efficiently.
    - Maintain two rolling hashes:
        1. Prefix hash (left to right)
        2. Suffix hash (right to left)
    - Compare at each step for a match; store the longest match length.

    Why Double Hashing?
    - Reduces the probability of collisions to almost zero.
    - Two different bases and two large mod values are used.

    Constraints:
    - String length ≤ 10^6 → Need O(n) solution, O(1) extra space.
*/

class Solution {
    public int getLPSLength(String s) {
        int base1 = 31, base2 = 37; // Two different bases for double hashing
        int mod1 = 1000000007, mod2 = 1000000009; // Large prime mod values

        int p1 = 1, p2 = 1; // Powers of base1 and base2
        int n = s.length();

        // hash1 → prefix hash, hash2 → suffix hash
        int[] hash1 = new int[] {0, 0}; // [mod1, mod2]
        int[] hash2 = new int[] {0, 0}; // [mod1, mod2]
        int ans = 0;

        // Loop till n-1 (cannot take full string as prefix or suffix)
        for (int i = 0; i < n - 1; i++) {

            // Here we update prefix hash (adding new char from left to right)
            hash1[0] = (int)((hash1[0] + 1L * (s.charAt(i) - 'a' + 1) * p1 % mod1) % mod1);
            hash1[1] = (int)((hash1[1] + 1L * (s.charAt(i) - 'a' + 1) * p2 % mod2) % mod2);

            // Here we update suffix hash (adding new char from right to left)
            hash2[0] = (int)((1L * hash2[0] * base1 % mod1 + (s.charAt(n - i - 1) - 'a' + 1)) % mod1);
            hash2[1] = (int)((1L * hash2[1] * base2 % mod2 + (s.charAt(n - i - 1) - 'a' + 1)) % mod2);

            // If both hash pairs match, we found a longer LPS candidate
            if (hash1[0] == hash2[0] && hash1[1] == hash2[1]) {
                ans = i + 1;
            }

            // Update powers for the next prefix position
            p1 = (int)(1L * p1 * base1 % mod1);
            p2 = (int)(1L * p2 * base2 % mod2);
        }

        return ans; // Return the length of longest prefix-suffix match
    }
}

/*
    Time Complexity: O(n) → Single pass through the string
    Space Complexity: O(1) → Only a few integer variables used
*/
