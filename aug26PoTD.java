/*
    PoTD Date: 26th Aug 2025

    Quick Flashcard:
    ----------------
    Problem:
    - Given two strings s1 and s2.
    - Check whether s1 is a subsequence of s2.
    - A subsequence means: characters of s1 appear in s2 in the same order, 
      but not necessarily contiguous.

    Example:
    s1 = "AXY", s2 = "YADXCP" → false
    s1 = "gksrek", s2 = "geeksforgeeks" → true

    Approach:
    - Use two pointers (i for s1, j for s2).
    - Traverse both strings:
        • If chars match → move both pointers.
        • Else → move pointer j of s2.
    - If all chars of s1 are matched (i == n) → s1 is subsequence of s2.
*/

class Solution {
    public boolean isSubSeq(String s1, String s2) {
        int n = s1.length(), m = s2.length();

        int i = 0; // pointer for s1
        int j = 0; // pointer for s2

        // Traverse s2 until either s1 is fully matched or s2 ends
        while (i < n && j < m) {
            if (s1.charAt(i) == s2.charAt(j)) {
                // Match found → move pointer of s1
                i++;
            }
            // Always move pointer of s2
            j++;
        }

        // If all characters of s1 were matched → true, else false
        return i == n;
    }
};

/*
    Time Complexity:
    - O(m), where m = length of s2 (we may scan entire s2 once).

    Space Complexity:
    - O(1), constant extra space used.
*/
