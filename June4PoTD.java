// June 4, 2025 – GFG PoTD 2.0

// 🧩 Question: Longest Common Subsequence of Three Strings
/*
You're given 3 strings: s1, s2, and s3.
Your task is to find the **length** of the longest subsequence that is **common** in all three strings.

A subsequence is a sequence that appears in the same order but not necessarily contiguous.

Example:
Input: s1 = "geeks", s2 = "geeksfor", s3 = "geeksforgeeks"
Output: 5
Explanation: "geeks" is the longest subsequence present in all three strings.
*/


// ✅ Approach:
/*
We use **Dynamic Programming (DP)** to build solutions for smaller subproblems.
Instead of a full 3D DP array (which takes a lot of space), we only use two 2D arrays:
→ one to store previous results (`prev`)
→ one for current iteration (`curr`)

We iterate over every character in all 3 strings and check:
- If current characters of all three strings match, we extend the LCS.
- If not, we take the maximum from possible combinations to keep the LCS optimal.
*/

public class Solution {
    public int lcsOf3(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();

        // 2D arrays to keep track of LCS lengths in a space-efficient way
        int[][] prev = new int[len2 + 1][len3 + 1];
        int[][] curr = new int[len2 + 1][len3 + 1];

        // Go through each character of the first string
        for (int i = 1; i <= len1; i++) {

            // Traverse second string
            for (int j = 1; j <= len2; j++) {

                // Traverse third string
                for (int k = 1; k <= len3; k++) {

                    // If characters from all three strings match
                    if (s1.charAt(i - 1) == s2.charAt(j - 1) &&
                        s1.charAt(i - 1) == s3.charAt(k - 1)) {

                        // Add 1 to the previous LCS value
                        curr[j][k] = 1 + prev[j - 1][k - 1];

                    } else {
                        // Otherwise, take the best among skipping from any of the strings
                        curr[j][k] = Math.max(prev[j][k],
                                              Math.max(curr[j - 1][k], curr[j][k - 1]));
                    }
                }
            }

            // Move current row to previous for the next iteration
            for (int j = 0; j <= len2; j++) {
                System.arraycopy(curr[j], 0, prev[j], 0, len3 + 1);
            }
        }

        // Final answer is the value at bottom-right of the current table
        return curr[len2][len3];
    }
}
