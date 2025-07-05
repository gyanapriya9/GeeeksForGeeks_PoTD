// PoTD 05 July 2025
// Max Score from Subarray Minimums

// Layman Explanation:
// You're given an array and asked to pick two positions i and j such that i < j.
// In the subarray from i to j, you find the two *smallest* numbers and add them.
// Your goal is to find the *maximum score* among all such subarrays.
// But here's the trick: the max score from any subarray will always come from 
// two adjacent elements (i.e., arr[i] and arr[i+1]) because any larger subarray 
// that includes more elements won't give a better score — the smallest two won't
// be larger than the adjacent pair. So we only need to look at adjacent pairs.

class Solution {
    public int maxSum(int[] arr) {
        int n = arr.length;
        int maxScore = Integer.MIN_VALUE;

        // Traverse the array to check all adjacent pairs
        for (int i = 1; i < n; i++) {
            // Take current and previous element
            int first = arr[i - 1];
            int second = arr[i];

            // Since we need the two smallest, use Math.min and Math.max
            int min1 = Math.min(first, second);
            int min2 = Math.max(first, second);

            // Update maxScore if their sum is greater than the current max
            maxScore = Math.max(maxScore, min1 + min2);
        }

        return maxScore;
    }
}
