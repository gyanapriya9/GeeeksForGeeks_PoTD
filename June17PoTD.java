class Solution {

    public int minimumCoins(int[] arr, int k) {
        // 📅 Date: 17 June 2025
        // 🔹 Problem: Coin Piles
        // 🔹 Difficulty: Medium
        // 🔹 Platform: GeeksforGeeks

        // ✅ Approach:
        // First, sort the array to easily work with contiguous values.
        Arrays.sort(arr);
        int n = arr.length;

        // Use prefix sum to calculate coin removal efficiently.
        int[] prefix = new int[n];
        prefix[0] = arr[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + arr[i];
        }

        int ans = Integer.MAX_VALUE;
        int prev = 0;

        for (int i = 0; i < n; i++) {

            // For each element arr[i], assume we keep all piles with coins in [arr[i], arr[i] + k]

            // Skip duplicates to avoid redundant calculations
            if (i > 0 && arr[i] == arr[i - 1]) continue;

            // Remove:
            // All smaller piles → prefix[i - 1]
            if (i > 0) {
                prev = prefix[i - 1];
            }

            // Use upper_bound to find where piles exceed the allowed maximum (arr[i] + k)
            int target = arr[i] + k;
            int pos = upperBound(arr, target, i, n);

            // All piles > arr[i] + k → remove their extra coins beyond the limit
            int totalToRemove = prev;
            if (pos < n) {
                int extraCoins = prefix[n - 1] - prefix[pos - 1]; // Total coins after pos
                int allowedCoins = (n - pos) * (arr[i] + k); // What we are allowed to keep
                totalToRemove += (extraCoins - allowedCoins); // Remove the excess
            }

            // Keep track of the minimum total coins to remove
            ans = Math.min(ans, totalToRemove);
        }

        return ans;
    }

    // Binary search to find the first index where arr[pos] > key
    private int upperBound(int[] arr, int key, int low, int high) {
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] <= key) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
