// 📅 PoTD 01 July 2025
// 🔍 Substrings of length k with (k - 1) distinct characters

// Layman's Explanation:
// You're given a string and a number `k`. Your job is to count how many substrings
// of length `k` have **exactly (k - 1)** different characters.
// That means: in each such substring, there must be **only one repeating character**.

// Approach:
// We use a **sliding window** of length `k` to inspect each substring.
// While moving the window, we track character frequency and number of distinct characters.

class Solution {
    public int substrCount(String s, int k) {
        // Early exit: can't form substrings of length k if string is shorter
        if (k > s.length()) return 0;

        int n = s.length();

        // Stores how many times each character appears in current window
        int[] frequency = new int[26];

        // Counter for substrings meeting the condition
        int validSubstrings = 0;

        // Tracks number of different characters in the current window
        int distinctChars = 0;

        // First fill the window with (k - 1) characters
        for (int i = 0; i < k - 1; i++) {
            int idx = s.charAt(i) - 'a';
            frequency[idx]++;
            if (frequency[idx] == 1) distinctChars++;
        }

        // Start sliding the window from k-1 index onwards
        for (int i = k - 1; i < n; i++) {
            // Add rightmost character to the window
            int addIndex = s.charAt(i) - 'a';
            frequency[addIndex]++;
            if (frequency[addIndex] == 1) distinctChars++;

            // ✅ Check if window has exactly (k - 1) unique characters
            if (distinctChars == k - 1) validSubstrings++;

            // Slide the window: remove the leftmost character
            int removeIndex = s.charAt(i - k + 1) - 'a';
            frequency[removeIndex]--;
            if (frequency[removeIndex] == 0) distinctChars--;
        }

        // Final answer: number of valid substrings found
        return validSubstrings;
    }
}
