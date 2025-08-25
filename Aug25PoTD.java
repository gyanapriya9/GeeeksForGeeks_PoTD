/*
    PoTD Date: 25th Aug 2025

    Quick Flashcard:
    ----------------
    Problem:
    - You have an array arr[] where arr[i] = day when i-th flower blooms.
    - To form 1 bouquet, we need k *adjacent* flowers that have already bloomed.
    - We need to form exactly m bouquets.
    - Task: Find minimum days required OR return -1 if impossible.

    Key Observations:
    - Total flowers needed = m * k. If arr.length < m * k → impossible.
    - If possible, the answer lies between min(arr) and max(arr).

    Approach (Binary Search on Answer):
    1. Binary search on "days" (lo = 1, hi = max bloom day).
    2. For each mid = possible days:
       - Use a helper check() to count how many bouquets can be formed by day = mid.
       - If bouquets >= m → valid, try smaller (hi = mid - 1).
       - Else → not enough, increase days (lo = mid + 1).
    3. Return smallest valid day.

    Example:
    Input: m = 3, k = 2, arr = [3,4,2,7,13,8,5]
    - By day 8: [✔,✔,✔,✔,_,✔,✔] → 3 bouquets possible.
    - Answer = 8.
*/

class Solution {
    // Helper function: check if we can form at least m bouquets in "days"
    public boolean check(int[] arr, int k, int m, int days) {
        int bouquets = 0; // count of bouquets formed
        int cnt = 0;      // consecutive bloomed flowers counter

        // Traverse bloom days
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= days) {
                cnt++; // this flower bloomed by 'days'
            } else {
                // form bouquets from consecutive bloomed flowers
                bouquets += cnt / k;
                cnt = 0; // reset since streak is broken
            }
        }
        bouquets += cnt / k; // leftover streak

        return bouquets >= m; // return true if we can make at least m bouquets
    }

    public int minDaysBloom(int[] arr, int k, int m) {
        // Edge case: not enough flowers to form m bouquets
        if ((long) m * k > arr.length) return -1;

        int lo = 1; // min possible day
        int hi = Arrays.stream(arr).max().getAsInt(); // max bloom day
        int res = -1;

        // Binary search on days
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (check(arr, k, m, mid)) {
                res = mid;   // valid answer found
                hi = mid - 1; // try smaller day
            } else {
                lo = mid + 1; // need more days
            }
        }
        return res;
    }
}

/*
    Time Complexity:
    - Binary Search iterations: O(log(max(arr))).
    - Each check() takes O(n).
    - Total: O(n * log(max(arr))).

    Space Complexity:
    - O(1) extra space.
*/
