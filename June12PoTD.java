// June 12, 2025 – GFG PoTD 2.0

// 🧩 Question: K Closest Elements
/*
Given:
- A **sorted array** `arr[]` of unique integers
- An integer `k` and a target value `x`

Task:
- Return `k` elements closest to `x`, **excluding x if it exists**
- Closer is defined by:
   1. Smaller absolute difference from `x`
   2. If tied, prefer **larger** element (a > b)

Return the closest elements **in order of closeness**, not sorted.

Examples:
Input: arr = [1, 3, 4, 10, 12], k = 2, x = 4 ➝ Output: 3 1
Input: arr = [12, 16, 22, 30, 35, 39, 42, 45], k = 4, x = 35 ➝ Output: 39 30 42 45
*/

// ✅ Efficient Two-Pointer with Binary Search
/*
1. Use binary search to find the last index where arr[i] < x.
2. Initialize two pointers:
   - `left` starting from that index
   - `right` from the next index (skip x if present)
3. Use two-pointer approach to collect k closest elements:
   - Pick the element with smaller |arr[i] - x|
   - If tie, prefer the **larger element**
4. Return result array of k closest elements.
*/

class Solution {
    int[] printKClosest(int[] arr, int k, int x) {
        int n = arr.length;
        int low = 0, high = n - 1, pos = -1;

        // 🔍 Binary search for the last element less than x
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] < x) {
                pos = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // 📍 Two-pointer setup
        int left = pos;
        int right = pos + 1;

        // ⚠️ Skip the element equal to x
        if (right < n && arr[right] == x) right++;

        int[] res = new int[k];
        int count = 0;

        // 🧮 Collect k closest elements using both pointers
        while (left >= 0 && right < n && count < k) {
            int leftDiff = Math.abs(arr[left] - x);
            int rightDiff = Math.abs(arr[right] - x);

            // Pick closer one or break tie by picking larger
            if (leftDiff < rightDiff ||
               (leftDiff == rightDiff && arr[left] > arr[right])) {
                res[count++] = arr[left--];
            } else {
                res[count++] = arr[right++];
            }
        }

        // ➕ Fill remaining if any pointer exhausted
        while (left >= 0 && count < k) res[count++] = arr[left--];
        while (right < n && count < k) res[count++] = arr[right++];

        return res;
    }
}
