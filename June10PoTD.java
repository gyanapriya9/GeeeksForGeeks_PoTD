// June 10, 2025 – GFG PoTD 2.0

// 🧩 Question: Exactly One Swap
/*
Given:
- A string `s`.

Task:
- Return the number of **distinct strings** that can be formed by **exactly one swap** between two different indices (i < j).

Examples:
Input: "geek" ➝ Output: 6
Input: "aaaa" ➝ Output: 1
(Only one arrangement possible after any swap when all characters are the same)
*/

// ✅ Approach: Track unique swaps and detect repeated characters
/*
1. Initialize `ans = 0` to count valid distinct swaps.
2. Use a `map[26]` to keep track of character frequencies up to index i.
3. For each character at index `i`:
   - The number of **unique previous characters** is `i - map[c]`, where c = s[i].
   - Add this to `ans` as each swap with a different character gives a new string.
4. After the loop, check if any character appears more than once:
   - If so, add 1 to `ans` to account for swapping identical characters (which gives the original string itself).
*/

class Solution {
    int countStrings(String s) {
        int n = s.length();

        // 🔹 map to count frequency of characters seen so far
        int[] map = new int[26];

        int ans = 0;

        // 🔹 Count distinct swaps
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            // Count how many characters before i are not equal to current one
            ans += (i - map[ch - 'a']);

            // Update frequency of current character
            map[ch - 'a']++;
        }

        // 🔹 Check if any duplicate character exists
        for (int i = 0; i < 26; i++) {
            if (map[i] > 1) {
                ans++; // Account for the original string (due to duplicate swap)
                break;
            }
        }

        return ans;
    }
}
