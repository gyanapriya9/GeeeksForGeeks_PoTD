// June 2, 2025 – GFG PoTD 2.0

// Question Explanation:
/*
You are given a 2D grid of size n x m:
- Each cell contains either 0 or 1.
- 0 → cell is open and can be visited.
- 1 → cell is blocked (obstacle) and cannot be visited.

You start from the top-left cell (1,1) i.e., grid[0][0], and your goal is to reach the bottom-right cell (n,m) i.e., grid[n-1][m-1].
You may only move either **right** or **down** from each cell.
The goal is to find **how many unique paths** exist from the start to the destination, avoiding obstacles.

Note:
- Start or end can also be blocked (value 1).
- Return 0 in such cases.
*/


// Approach:
/*
We use Dynamic Programming to optimize the solution.
Instead of creating a 2D DP matrix, we use a 1D array (`dp`) to reduce space complexity.

Logic:
- dp[j] represents the number of ways to reach cell (i,j).
- If the cell has an obstacle, dp[j] = 0.
- Otherwise, dp[j] = dp[j] (from top) + dp[j - 1] (from left), if j > 0.
*/


// Solution:
class Solution {
    public int uniquePaths(int[][] grid) {
        int r = grid.length;             // number of rows
        int c = grid[0].length;          // number of columns

        int[] dp = new int[c];           // DP array for current row

        // Set base case: only 1 way to be at start if it's not blocked
        dp[0] = (grid[0][0] == 0) ? 1 : 0;

        // Iterate through each cell in the grid
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {

                // If the cell has an obstacle → reset to 0
                if (grid[i][j] == 1) {
                    dp[j] = 0;
                }
                // Else add paths from the left (if j > 0)
                else if (j > 0) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        // dp[c - 1] now holds the number of paths to bottom-right corner
        return dp[c - 1];
    }
}
