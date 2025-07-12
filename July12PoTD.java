// PoTD Date: 12/07/2025
// Problem: Gold Mine Problem
// Difficulty: Medium
// Description:
// Given a 2D grid 'mat' where mat[i][j] represents amount of gold at that cell,
// the miner can start from any row of the first column and move only to:
// → right
// ↗ right-up
// ↘ right-down
// The task is to find the path that gives the maximum gold collection possible.

// Approach:
// We'll use **Dynamic Programming**, starting from the second last column and updating
// each cell with the maximum gold that can be collected starting from that cell going right.
// Finally, we return the max value from the first column.

class Solution {
    public int maxGold(int[][] mat) {
        int n = mat.length;      // Number of rows
        int m = mat[0].length;   // Number of columns

        // Traverse the matrix from second last column to the first column
        for (int y = m - 2; y >= 0; y--) {
            for (int x = 0; x < n; x++) {

                int maxPrev = 0; // Track max gold that can be collected from right paths

                // Check ↗ right-up move (if not on top row)
                if (x - 1 >= 0 && y + 1 < m) {
                    maxPrev = Math.max(maxPrev, mat[x - 1][y + 1]);
                }

                // Check → right move
                if (y + 1 < m) {
                    maxPrev = Math.max(maxPrev, mat[x][y + 1]);
                }

                // Check ↘ right-down move (if not on bottom row)
                if (x + 1 < n && y + 1 < m) {
                    maxPrev = Math.max(maxPrev, mat[x + 1][y + 1]);
                }

                // Update current cell with max gold path value
                mat[x][y] += maxPrev;
            }
        }

        // Now, check first column for the maximum value (best starting point)
        int result = 0;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, mat[i][0]);
        }

        // Return the maximum gold that can be collected
        return result;
    }
}
