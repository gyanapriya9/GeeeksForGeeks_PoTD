// PoTD Date: 31st July 2025
// Problem: Powerful Integer
// Task: Find the maximum integer that appears in at least 'k' intervals from a list of closed intervals

/*
Flashcard-style Explanation:
-----------------------------------
What is required?
- Given 'n' closed intervals in the form of [start, end]
- Find the maximum integer that appears in at least 'k' of those intervals
- If no such integer exists, return -1

What approach are we using?
- We'll use a prefix sum technique with a TreeMap to efficiently manage the frequency changes at start and end+1 of each interval.
- Use TreeMap to keep keys sorted and simulate a sweep line across all interval points.

Why prefix sum?
- Instead of checking each number in all ranges (which is not scalable up to 10^9), we track changes at interval boundaries.
- We add +1 at the start and -1 at the end+1 of each interval.
- Then we traverse the keys in sorted order and maintain a running frequency count.
- Whenever this running count is >= k, we update our answer with the current number.
*/

// Java implementation
class Solution {
    public int powerfulInteger(int[][] intervals, int k) {
        // TreeMap to store interval boundaries and their frequency changes
        TreeMap<Integer, Integer> map = new TreeMap<>();

        // Step 1: Mark interval starts with +1 and ends+1 with -1
        for (int i = 0; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];

            // Increment count at interval start
            map.put(start, map.getOrDefault(start, 0) + 1);

            // Decrement count at end+1 to simulate closing of interval
            map.put(end + 1, map.getOrDefault(end + 1, 0) - 1);
        }

        int ans = -1; // Variable to store the maximum powerful integer
        int temp = 0; // Running sum of frequencies to track active intervals

        // Step 2: Traverse the TreeMap in sorted key order (simulates sweep line)
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int point = entry.getKey();   // Current point in the number line
            int delta = entry.getValue(); // Frequency change at this point

            if (delta >= 0) {
                // Add delta (start of interval)
                temp += delta;

                // If current point has at least k active intervals, update answer
                if (temp >= k) {
                    ans = point;
                }
            } else {
                // If exiting interval but still >= k before update, record (point - 1)
                if (temp >= k) {
                    ans = point - 1;
                }
                // Subtract delta (end of interval)
                temp += delta;
            }
        }

        return ans;
    }
}

/*
Time Complexity:
- O(N log N), where N is the number of intervals.
  - Each interval adds 2 operations to the TreeMap (start and end+1).
  - TreeMap operations are log N due to internal red-black tree.

Space Complexity:
- O(N), for storing 2*N keys (start and end+1) in the TreeMap.
*/
