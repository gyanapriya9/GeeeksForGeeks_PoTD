/*
    Today Date PoTD: 10th Aug 2025

    Quick Flashcard:
    Problem:
    - Given a string s, count all palindromic substrings of length >= 2.
    - A palindrome reads the same forwards and backwards.
    - Output the count of such substrings.

    Example:
    s = "abaab" → Palindromes: "aba", "aa", "baab" → Count = 3
    s = "aaa"   → Palindromes: "aa", "aa", "aaa"  → Count = 3

    Approach:
    - Use **Center Expansion** technique.
    - For each character in the string:
        1. Consider it as the middle of an odd-length palindrome.
        2. Consider the gap between it and the next character for even-length palindromes.
    - Expand outwards while left == right characters match.
    - Count only substrings with length >= 2.

    Why Center Expansion?
    - Simple to implement.
    - Checks all possible palindrome centers in O(n^2) time.
*/

class Solution {
    public int countPS(String s) {
        int n = s.length();
        int count = 0;

        // Odd length palindromes: center is a single character
        for (int i = 0; i < s.length(); i++) {
            int left = i - 1;   // Start left of center
            int right = i + 1;  // Start right of center
            while (left >= 0 && right < n) {
                if (s.charAt(left) == s.charAt(right)) {
                    count++; // Found a palindrome
                } else {
                    break;   // Mismatch, stop expanding
                }
                left--;
                right++;
            }
        }

        // Even length palindromes: center is between two characters
        for (int i = 0; i < s.length(); i++) {
            int left = i;       // Left center
            int right = i + 1;  // Right center
            while (left >= 0 && right < n) {
                if (s.charAt(left) == s.charAt(right)) {
                    count++; // Found a palindrome
                } else {
                    break;   // Mismatch, stop expanding
                }
                left--;
                right++;
            }
        }

        return count;
    }
}

/*
    Time Complexity: O(n^2) → For each character, expanding takes O(n) in worst case.
    Space Complexity: O(1)  → No extra data structures used.
*/
