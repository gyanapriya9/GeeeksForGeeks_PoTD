// PoTD: 22nd July 2025
// --------------------------------------------
// 🔹 Problem: Sum of Subarrays
// 🔹 Given: An array of integers
// 🔹 Task: Find the sum of all possible subarray sums
// 🔹 Key Insight: 
//     Each element `arr[i]` will appear in (i + 1) * (n - i) subarrays
//     So its total contribution is: arr[i] * (i + 1) * (n - i)
// 🔹 Approach:
//     Use the above formula and iterate through the array to compute total sum
// --------------------------------------------

class Solution {
    public int subarraySum(int[] arr) {
        int n = arr.length;
        int result = 0;

        // Loop through each element to calculate contribution
        for (int i = 0; i < n; i++) {
            // Each element arr[i] contributes to (i+1)*(n-i) subarrays
            result += (arr[i] * (i + 1) * (n - i));
        }

        // Return the sum of all subarray sums
        return result;
    }
}

// --------------------------------------------
// 🔹 Time Complexity: O(n)
// 🔹 Space Complexity: O(1)
// --------------------------------------------
