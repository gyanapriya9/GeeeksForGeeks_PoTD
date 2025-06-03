// June 3, 2025 – GFG PoTD 2.0

// Question Explanation:
/*
You are given a string `s` and an integer `k`.
Your task is to count all substrings (not necessarily distinct) of `s` that contain exactly **k distinct characters**.

Examples:
Input: s = "abc", k = 2 → Output: 2 → Substrings: ["ab", "bc"]
Input: s = "aba", k = 2 → Output: 3 → Substrings: ["ab", "ba", "aba"]
Input: s = "aa",  k = 1 → Output: 3 → Substrings: ["a", "a", "aa"]
*/

// Key Observation:
/*
To count substrings with exactly `k` distinct characters,
you can calculate:
→ count of substrings with at most k distinct characters
→ minus the count of substrings with at most (k - 1) distinct characters
*/


// Back-end complete function template for Java

class Solution {

    // Helper function: Count substrings with at most `k` distinct characters
    static int count(String s, int k) {
        int n = s.length();
        int ans = 0;

        int[] freq = new int[26];  // frequency array for characters 'a' to 'z'
        int distinctCnt = 0;
        int i = 0;

        for (int j = 0; j < n; j++) {
            // Expand the window by including character at j
            freq[s.charAt(j) - 'a']++;
            if (freq[s.charAt(j) - 'a'] == 1) {
                distinctCnt++;  // new character added
            }

            // If distinct count > k, shrink window from the left
            while (distinctCnt > k) {
                freq[s.charAt(i) - 'a']--;
                if (freq[s.charAt(i) - 'a'] == 0) {
                    distinctCnt--;
                }
                i++;
            }

            // All substrings ending at j with <= k distinct chars
            ans += j - i + 1;
        }

        return ans;
    }

    // Main function: Return substrings with exactly `k` distinct characters
    static int countSubstr(String s, int k) {
        // Substrings with exactly k distinct = at most k - at most (k-1)
        return count(s, k) - count(s, k - 1);
    }
}
