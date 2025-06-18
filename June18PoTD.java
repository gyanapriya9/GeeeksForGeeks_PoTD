class Solution {

    // 🔁 Precompute all palindromic substrings in s using bottom-up DP
    public static void palindromes(String s, boolean[][] dp) {
        int n = s.length();

        // Single characters are always palindromes
        for (int i = 0; i < n; ++i)
            dp[i][i] = true;

        // Check palindromes of length 2
        for (int i = 0; i < n - 1; ++i)
            dp[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));

        // Check palindromes of length ≥ 3
        for (int len = 3; len <= n; ++len) {
            for (int i = 0; i <= n - len; ++i) {
                int j = i + len - 1;
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && dp[i + 1][j - 1];
            }
        }
    }

    // 🔁 Recursive function to generate all palindromic partitions
    public static void backtrack(int idx, String s, ArrayList<String> curr,
                                 ArrayList<ArrayList<String>> res, boolean[][] dp) {

        // If index reaches end, we store the current partition
        if (idx == s.length()) {
            res.add(new ArrayList<>(curr));
            return;
        }

        // Try all possible substrings starting from idx
        for (int i = idx; i < s.length(); ++i) {
            // If s[idx..i] is palindrome, include and recurse
            if (dp[idx][i]) {
                curr.add(s.substring(idx, i + 1)); // Choose
                backtrack(i + 1, s, curr, res, dp); // Explore
                curr.remove(curr.size() - 1); // Un-choose (Backtrack)
            }
        }
    }

    // ✅ Main driver function to return all palindromic partitions
    public static ArrayList<ArrayList<String>> palinParts(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n]; // DP table to track palindromes

        palindromes(s, dp); // Step 1: Preprocess palindromes

        ArrayList<ArrayList<String>> res = new ArrayList<>(); // Final result
        ArrayList<String> curr = new ArrayList<>(); // Current partition path

        backtrack(0, s, curr, res, dp); // Step 2: Begin backtracking from index 0

        return res;
    }
}
