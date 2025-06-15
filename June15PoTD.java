// GFG PoTD – June 14, 2025 – Smallest Divisor
import java.util.*;

class Solution {
    public int smallestDivisor(int[] arr, int k) {
        int low = 1;
        int high = Arrays.stream(arr).max().getAsInt();
        int result = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int sum = 0;

            for (int num : arr) {
                sum += (num + mid - 1) / mid;  // ceil(num / mid)
            }

            if (sum <= k) {
                result = mid;   // Try smaller divisor
                high = mid - 1;
            } else {
                low = mid + 1;  // Increase divisor
            }
        }

        return result;
    }
}
