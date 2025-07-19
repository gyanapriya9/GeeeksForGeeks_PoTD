// Problem of the Day: Count Unique Vowel Strings
// Date: July 19, 2025

/*
Flashcard Summary:
------------------
Q: What is required?
   - Identify all unique vowels in the string.
   - For each vowel, pick one of its occurrences.
   - Find all permutations of the selected vowels.

Q: What is the approach?
   - Count frequency of each vowel in the string.
   - For each distinct vowel, choose one of its occurrences.
   - Use bitmasking + recursion to generate all possible permutations.
   - Multiply frequency of vowels while recursively selecting unused ones.
*/

public class Solution {

    // Recursive function to count permutations using bitmask
    static int count(int mask, int k, int[] freq, int[] dp) {
        // Base case: all vowels are used (i.e., mask has all bits set)
        if (mask == (1 << k) - 1) {
            return 1;
        }

        // If already calculated for this mask, return stored result
        if (dp[mask] != -1) {
            return dp[mask];
        }

        int total = 0;

        // Try all unused vowels (those whose bit is not set)
        for (int i = 0; i < k; i++) {
            if ((mask & (1 << i)) == 0) {
                // Add freq[i] * recursive result of using this vowel
                total += freq[i] * count(mask | (1 << i), k, freq, dp);
            }
        }

        dp[mask] = total; // Memoize result
        return total;
    }

    static int vowelCount(String s) {
        String vowels = "aeiou";
        int[] totalFreq = new int[5]; // To store frequency of a, e, i, o, u

        // Count frequency of each vowel in the string
        for (char c : s.toCharArray()) {
            int index = vowels.indexOf(c);
            if (index != -1) {
                totalFreq[index]++;
            }
        }

        // Extract only used vowels and their frequencies
        int[] usedFreq = new int[5]; // max 5 vowels
        int k = 0; // number of distinct vowels found
        for (int i = 0; i < 5; i++) {
            if (totalFreq[i] > 0) {
                usedFreq[k++] = totalFreq[i];
            }
        }

        // If no vowels are found, return 0
        if (k == 0) return 0;

        // Initialize memoization array for 2^k possible states
        int[] dp = new int[1 << k];
        for (int i = 0; i < dp.length; i++) dp[i] = -1;

        return count(0, k, usedFreq, dp);
    }

    public static void main(String[] args) {
        System.out.println(vowelCount("omd"));      // Output: 1  (vowel: o)
        System.out.println(vowelCount("aaxyze"));   // Output: 2  (vowels: a, e)
        System.out.println(vowelCount("aeiou"));    // Output: 120 (5! permutations)
        System.out.println(vowelCount("abcd"));     // Output: 1  (vowel: a)
        System.out.println(vowelCount("xyz"));      // Output: 0  (no vowels)
    }
}

/*
Time Complexity:
- O(k! * 2^k), where k = number of distinct vowels (max 5)
- Because for each state we generate permutations via recursive bitmasking

Space Complexity:
- O(2^k) for the dp array
- O(k) for usedFreq array
*/
