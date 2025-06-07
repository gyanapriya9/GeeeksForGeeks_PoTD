// June 7, 2025 – GFG PoTD 2.0

// 🧩 Question: Longest Span in Two Binary Arrays
/*
Given:
- Two binary arrays a1[] and a2[] of equal length.
Find:
- The length of the longest span (i, j) such that the sum of elements from i to j in a1
  is equal to the sum of elements from i to j in a2.

Example:
a1 = [0,1,0,0,0,0], a2 = [1,0,1,0,0,1]
Output: 4 (longest span from index 1 to 4 where sums are equal)

Approach:
- Calculate running sums for both arrays.
- At each index, find difference = sum1 - sum2.
- If difference is zero, it means from start to this index sums are equal.
- Use a hashmap to store first occurrence of each difference.
- When the same difference appears again, the subarray between first occurrence + 1 to current index
  has equal sums in both arrays.
- Keep track of max length of such subarrays.
*/

// ✅ Approach Explanation:
/*
1. Initialize sums for both arrays as 0.
2. Create a hashmap to store the first index where a particular difference (sum1 - sum2) occurs.
3. Iterate through both arrays simultaneously:
   - Update running sums.
   - Compute current difference = sum1 - sum2.
   - If difference is 0, update max length as current index + 1.
   - Else if difference already exists in hashmap, update max length as max of current max and
     distance between current index and first occurrence of difference.
   - Else, store current index for this difference in hashmap.
4. Return the max length found.
*/

class Solution {
    public int longestCommonSum(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int res = 0;

        // hashmap to store first occurrence of each difference
        HashMap<Integer, Integer> diffMap = new HashMap<>();

        int sum1 = 0, sum2 = 0;

        for (int i = 0; i < n; i++) {
            sum1 += arr1[i];    // running sum of arr1
            sum2 += arr2[i];    // running sum of arr2

            int currentDiff = sum1 - sum2;

            if (currentDiff == 0) {
                // sums from 0 to i are equal, update max length
                res = Math.max(res, i + 1);
            }
            else if (diffMap.containsKey(currentDiff)) {
                // difference seen before, subarray between first occurrence+1 to i has equal sums
                res = Math.max(res, i - diffMap.get(currentDiff));
            } else {
                // store first occurrence of this difference
                diffMap.put(currentDiff, i);
            }
        }

        return res;  // longest span length
    }
}
