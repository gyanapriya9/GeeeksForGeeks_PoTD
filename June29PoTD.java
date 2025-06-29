// 📅 PoTD 29 June 2025
// 🧠 Split Array Largest Sum

// 💬 Layman’s Explanation:
// We are given an array and a number k. We must split the array into exactly `k` parts (they must be **continuous chunks**).
// Our goal is to **minimize the largest sum** among all parts.
// Think of it as balancing work among k workers – we want no one to get too much workload.

// ✅ Strategy:
// We'll use **binary search on the answer** (on the "maximum sum") and validate each guess using a helper function.

class Solution {

    // 🔧 Helper function to check if we can split array into ≤ k parts
    // such that no part has sum > 'limit'
    private static boolean canSplit(int[] arr, int k, int limit) {
        int splits = 1;  // Start with one subarray
        int currentSum = 0;

        for (int num : arr) {
            // If any single number is greater than limit, it's not possible
            if (num > limit) return false;

            // Accumulate current number into the current chunk
            currentSum += num;

            // If sum exceeds limit, start a new subarray
            if (currentSum > limit) {
                splits++;
                currentSum = num;
            }
        }

        // Return true if we can split into k or fewer parts
        return splits <= k;
    }

    public int splitArray(int[] arr, int k) {
        int n = arr.length;

        // Minimum possible value of max sum = max element in array
        int low = Arrays.stream(arr).max().getAsInt();

        // Maximum possible value = total sum (if no splits)
        int high = Arrays.stream(arr).sum();

        int answer = high;  // Start with worst case

        // 🔍 Binary search to find the smallest valid max sum
        while (low <= high) {
            int mid = (low + high) / 2;

            // Can we split array into ≤ k subarrays with max sum ≤ mid?
            if (canSplit(arr, k, mid)) {
                answer = mid;  // It's a possible answer, try smaller
                high = mid - 1;
            } else {
                low = mid + 1; // Not possible, try bigger
            }
        }

        return answer;
    }
}
