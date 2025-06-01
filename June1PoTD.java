//June 1, 2025 – GFG PoTD 2.0


//Question Explanation 
/*
You are given two square matrices (mat1 and mat2) of size n x n.
Both matrices are strictly sorted — not just left-to-right in rows, but entire matrix behaves like a sorted 1D array if flattened.
Now, you’re given a number x, and your goal is to count how many pairs of elements — one from mat1 and one from mat2 — add up to x.
For example, if x = 21, and mat1 has 1 and mat2 has 20, that's a valid pair → 1 + 20 = 21.
*/

//Approach:
/*
Start from the smallest element in mat1 and largest in mat2.
Check their sum. If it’s equal to x, we found a pair!
If the sum is too small, move to next higher in mat1.
If too big, move to next lower in mat2.
Keep doing this efficiently until we exhaust either matrix.
*/

//Solution:

class Solution {
    int countPairs(int[][] mat1, int[][] mat2, int target) {
        int row1 = 0, col1 = 0;  // Start from beginning of mat1
        int row2 = mat2.length - 1;  // Start from end of mat2
        int col2 = mat2[0].length - 1;

        int totalPairs = 0;

        // Keep checking while we’re still inside both matrices
        while (row1 < mat1.length && row2 >= 0) {
            int num1 = mat1[row1][col1]; // current number from mat1
            int num2 = mat2[row2][col2]; // current number from mat2

            int sum = num1 + num2;

            if (sum == target) {
                totalPairs++;    // Found a valid pair
                col1++;          // move to next column in mat1
                col2--;          // move to previous column in mat2
            } else if (sum < target) {
                col1++;          // sum too small → move to next bigger number in mat1
            } else {
                col2--;          // sum too big → move to smaller number in mat2
            }

            // If we reach the end of a row in mat1, jump to next row
            if (col1 == mat1[0].length) {
                col1 = 0;
                row1++;
            }

            // If we go out of bounds in mat2, move to the previous row
            if (col2 < 0) {
                col2 = mat2[0].length - 1;
                row2--;
            }
        }

        return totalPairs;
    }
}


