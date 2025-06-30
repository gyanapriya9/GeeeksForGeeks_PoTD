// PoTD 30 June 2025
// Max Min Height

// Layman's Explanation:
// You have a row of flowers, each with some initial height. You can water `w` consecutive flowers every day, and you can do this for `k` days.
// Every time you water a flower, its height increases by 1.
// Your goal is to **maximize the minimum height** of any flower after all watering is done.

// Approach:
// We'll use **Binary Search** on the answer (possible minimum height).
// For each guess, we'll simulate watering using a **prefix sum technique** to efficiently apply range updates.

class Solution {

    // Helper function to check if we can reach at least 'targetHeight' for all flowers
    public boolean canReachHeight(int[] arr, int k, int w, int targetHeight) {
        int n = arr.length;

        // This array helps apply water updates efficiently using prefix sums
        int[] waterEffect = new int[n + 1];
        int usedDays = 0;

        for (int i = 0; i < n; i++) {
            // Accumulate previous watering effect
            if (i > 0) {
                waterEffect[i] += waterEffect[i - 1];
            }

            // Effective height after past watering
            int currentHeight = arr[i] + waterEffect[i];

            // If current height is less than target, we need more watering
            if (currentHeight < targetHeight) {
                int need = targetHeight - currentHeight;

                // Water current position (affects next w flowers)
                waterEffect[i] += need;
                if (i + w < n) {
                    waterEffect[i + w] -= need;
                }

                usedDays += need;
                if (usedDays > k) return false; // Out of days
            }
        }

        return true;
    }

    public int maxMinHeight(int[] arr, int k, int w) {
        int n = arr.length;

        // Minimum height in the original array
        int minHeight = Arrays.stream(arr).min().getAsInt();

        // Maximum possible height after watering
        int maxHeight = minHeight + k;
        int bestHeight = minHeight;

        // 🧪 Binary search for the best achievable minimum height
        while (minHeight <= maxHeight) {
            int mid = (minHeight + maxHeight) / 2;

            if (canReachHeight(arr, k, w, mid)) {
                bestHeight = mid;       // Valid, try to go higher
                minHeight = mid + 1;
            } else {
                maxHeight = mid - 1;    // Not possible, try lower
            }
        }

        return bestHeight;
    }
}
