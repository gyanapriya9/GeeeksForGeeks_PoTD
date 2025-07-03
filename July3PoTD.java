// PoTD 03 July 2025
// Longest Substring with Exactly K Unique Characters

// Layman's Explanation:
// You are given a string and a number 'k'. 
// You need to find the length of the longest piece (substring) of the string 
// that contains exactly k different characters.
// If it's not possible, return -1.

class Solution {
    public int longestKSubstr(String s, int k) {
        int n = s.length();
        if (n == 0 || k == 0) return -1;

        // Frequency tracker for characters from 'a' to 'z'
        int[] charCount = new int[26];

        int left = 0, right = 0;
        int distinct = 0;
        int longest = -1;

        // Expand window with right pointer
        while (right < n) {
            int currentCharIndex = s.charAt(right) - 'a';

            // If this is the first time this character appears, increase distinct count
            if (charCount[currentCharIndex] == 0) {
                distinct++;
            }

            // Update the frequency of the current character
            charCount[currentCharIndex]++;

            // Shrink window from the left until we have at most k distinct characters
            while (distinct > k) {
                int removeCharIndex = s.charAt(left) - 'a';
                charCount[removeCharIndex]--;
                if (charCount[removeCharIndex] == 0) {
                    distinct--;
                }
                left++;
            }

            // If exactly k unique characters are found, check if it's the longest
            if (distinct == k) {
                longest = Math.max(longest, right - left + 1);
            }

            // Move right pointer forward
            right++;
        }

        return longest;
    }
}
