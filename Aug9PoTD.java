/*
    Today Date PoTD: 9th Aug 2025

    Quick Flashcard:
    Problem:
    - Given a string s, find the length of the longest **periodic proper prefix**.
    - A periodic proper prefix is a non-empty prefix (not the full string)
      that, when repeated enough times, produces a string starting with s.
    - If no such prefix exists, return -1.

    Example:
    s = "abcab" → Answer = 3 ("abc")
    s = "ababd" → Answer = -1

    Approach:
    - Use **Double Rolling Hashing** for efficient substring comparison.
    - Rolling hash allows O(1) substring comparison after O(n) preprocessing.
    - Check each possible prefix length and verify if repeating it can generate the string's start.
    - Mark visited multiples of lengths to skip redundant checks.

    Why Rolling Hash?
    - Substring comparisons in O(1) instead of O(k).
    - Reduces risk of collisions using two bases.

    Constraints:
    - String length ≤ 10^5 → O(n^2) naive would be too slow, so use hashing-based checks.
*/

import java.util.Arrays;

class Solution {
    // Helper class for rolling hash computations
    static class RollingHash {
        final int mod = (int)1e9 + 7; // Large prime modulus
        final int base1 = 4441; // First hash base
        final int base2 = 7817; // Second hash base

        int length;
        String input;
        int[][] preHash; // Stores prefix hashes for both bases
        int[][] power;   // Stores base powers for both bases

        public RollingHash(String str) {
            input = str;
            length = str.length();
            preHash = new int[length + 1][2];
            power = new int[length + 1][2];

            for (int[] row : power) Arrays.fill(row, 1);
            buildHashes(); // Precompute prefix hashes and powers
        }

        int add(int a, int b) {
            return (a + b) % mod;
        }

        int subtract(int a, int b) {
            return (a - b + mod) % mod;
        }

        int multiply(int a, int b) {
            return (int)(((long)a * b) % mod);
        }

        // Precomputing prefix hashes and powers
        void buildHashes() {
            for (int i = 0; i < length; i++) {
                preHash[i + 1][0] = add(multiply(preHash[i][0], base1), input.charAt(i));
                preHash[i + 1][1] = add(multiply(preHash[i][1], base2), input.charAt(i));

                power[i + 1][0] = multiply(power[i][0], base1);
                power[i + 1][1] = multiply(power[i][1], base2);
            }
        }

        // Get hash of substring [left, right)
        int[] getHash(int left, int right) {
            int[] result = new int[2];
            for (int b = 0; b < 2; ++b) {
                result[b] = subtract(preHash[right][b], multiply(preHash[left][b], power[right - left][b]));
            }
            return result;
        }
    }

    // Main function to get longest periodic proper prefix
    static int getLongestPrefix(String s) {
        int n = s.length();
        RollingHash rh = new RollingHash(s); // Precompute rolling hashes

        int[] mark = new int[n + 1]; // To skip already checked multiples
        int maxLenPre = -1; // Store the longest length found

        for (int len = 1; len < n; len++) {
            if (mark[len] != 0) continue; // Skip already processed lengths

            int idx = 0;
            boolean isPrefix = true; // Assume current len is valid until proven otherwise

            // Check if repeating prefix of length `len` forms start of s
            while (idx < n) {
                int remLen = n - idx;

                // If last chunk is shorter than len, compare partial match
                if (remLen <= len) {
                    int[] hs1 = rh.getHash(idx, n);
                    int[] hs2 = rh.getHash(0, remLen);
                    if (!Arrays.equals(hs1, hs2)) {
                        isPrefix = false;
                    }
                    break;
                }

                // Compare full chunk with the prefix
                int[] hs1 = rh.getHash(idx, idx + len);
                int[] hs2 = rh.getHash(0, len);
                if (!Arrays.equals(hs1, hs2)) {
                    isPrefix = false;
                    break;
                }
                idx += len;
            }

            // If valid, mark multiples and update maximum length
            if (isPrefix) {
                for (int x = len; x < n; x += len) {
                    mark[x] = 1;
                    maxLenPre = Math.max(maxLenPre, x);
                }
            }
        }

        return maxLenPre;
    }

    public static void main(String[] args) {
        String s = "abcab";
        System.out.println(getLongestPrefix(s)); // Expected: 3
    }
}

/*
    Time Complexity: O(n * sqrt(n)) in practice due to marking optimization
    Space Complexity: O(n) for hash and power storage
*/
