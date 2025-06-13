// June 13, 2025 – GFG PoTD 2.0

// 🐒 Problem: Koko Eating Bananas
/*
Given:
- An array `arr[]` representing piles of bananas.
- An integer `k` representing hours Koko has.

Rules:
- Koko can eat up to `s` bananas/hour from one pile per hour.
- If pile has fewer than `s`, she eats all.
- She eats from **one pile per hour**.

Task:
- Find the **minimum possible value of s** such that she can eat all bananas in `k` hours.

Examples:
Input: arr = [5, 10, 3], k = 4 ➝ Output: 5
  (She eats: 5 (pile1), 5+5 (pile2), 3 (pile3) ➝ total 4 hrs)

Input: arr = [5, 10, 15, 20], k = 7 ➝ Output: 10
  (She can do it in 6 hours: 1+1+2+2)
*/

// ✅ Binary Search on Answer + Greedy Check
/*
1. We binary search on the possible eating speeds: [1 ... max(arr)].
2. For each speed, we check if it's enough to finish all bananas in <= k hours.
3. Use ceiling division to count hours needed for each pile.
*/

class Solution {

    // ✅ Check if given eating speed (mid) is enough
    boolean check(int[] arr, int mid, int k) {
        int hours = 0;
        for (int pile : arr) {
            // Ceil division: hours += Math.ceil(pile / mid)
            hours += pile / mid;
            if (pile % mid != 0) hours++;
        }
        return hours <= k;
    }

    public int kokoEat(int[] arr, int k) {
        int lo = 1;
        int hi = Arrays.stream(arr).max().getAsInt(); // Max possible speed
        int res = hi;

        // 🔍 Binary search for minimum valid speed
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (check(arr, mid, k)) {
                res = mid;       // Try lower speed
                hi = mid - 1;
            } else {
                lo = mid + 1;    // Need higher speed
            }
        }

        return res;
    }
}
