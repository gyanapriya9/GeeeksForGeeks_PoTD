// ✅ June 14, 2025 – GFG PoTD

/*
🎯 Problem: Koko Eating Bananas

You are given an array `arr[]` of banana piles and an integer `k` representing the number of hours Koko has to eat all bananas.

Rules:
- Each hour, she eats from one pile only.
- She eats up to `s` bananas per hour from a pile.
- If the pile has less than `s`, she eats the whole pile in one hour.

Goal:
- Find the **minimum value of `s`** such that she finishes all bananas within `k` hours.

🧠 Approach:
- Use Binary Search on possible eating speeds (from 1 to max pile size).
- For each candidate speed, calculate total hours needed using:
    time = ⌈pile / speed⌉ for each pile.
- Find the minimum speed where total time ≤ k.

⏱️ Time Complexity: O(n * log(max)), where max = max value in arr[]
*/

import java.util.*;

class Solution {

    // ✅ Helper function to check if Koko can finish at 'speed' in 'k' hours
    private boolean canEatAll(int[] arr, int speed, int k) {
        int hours = 0;
        for (int pile : arr) {
            hours += (pile + speed - 1) / speed; // Efficient way to compute ceil(pile / speed)
        }
        return hours <= k;
    }

    public int kokoEat(int[] arr, int k) {
        int low = 1;
        int high = Arrays.stream(arr).max().getAsInt();
        int result = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canEatAll(arr, mid, k)) {
                result = mid;     // Try smaller speed
                high = mid - 1;
            } else {
                low = mid + 1;    // Need faster speed
            }
        }

        return result;
    }
}
