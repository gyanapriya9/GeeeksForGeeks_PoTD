// Today Date PoTD : 7th Aug 2025
// Problem Title: Difference Check

/*
Flashcard Style Problem Explanation:
-------------------------------------
Task: Given an array of time strings in "HH:MM:SS" 24-hour format,
      return the **minimum difference in seconds** between any two time values.
Note: The clock wraps at midnight (e.g., "23:59:59" and "00:00:00" differ by 1 second).

Approach:
- Convert all time strings to seconds from 00:00:00.
- Mark each second seen using a boolean array of size 86400 (24 * 3600).
- Track positions of valid seconds and compute minimum difference linearly.
- Consider wrap-around difference (from last time to first across midnight).
*/

class Solution {

    // Converts time in "HH:MM:SS" format to total seconds
    private static int toSeconds(String time) {
        int h = Integer.parseInt(time.substring(0, 2)); // Extract hours
        int m = Integer.parseInt(time.substring(3, 5)); // Extract minutes
        int s = Integer.parseInt(time.substring(6, 8)); // Extract seconds
        return h * 3600 + m * 60 + s; // Total seconds from midnight
    }

    public static int minDifference(String[] arr) {
        int totalSec = 24 * 3600; // Total seconds in a day = 86400
        boolean[] seen = new boolean[totalSec]; // Track if second is already seen

        // Convert all time strings to seconds and mark in seen[]
        for (String time : arr) {
            int sec = toSeconds(time);
            if (seen[sec]) return 0; // If duplicate time found, min diff is 0
            seen[sec] = true;
        }

        int first = -1, last = -1, prev = -1, minDiff = Integer.MAX_VALUE;

        // Traverse the 86400 seconds and find minimum difference
        for (int i = 0; i < totalSec; i++) {
            if (!seen[i]) continue;

            if (prev != -1) {
                minDiff = Math.min(minDiff, i - prev); // Update min diff between current and previous
            } else {
                first = i; // Store first valid time
            }

            prev = i; // Update previous
            last = i; // Update last valid time
        }

        // Consider wrap-around case (from last to first across midnight)
        int wrap = first + totalSec - last;
        return Math.min(minDiff, wrap);
    }

    // Sample test
    public static void main(String[] args) {
        String[] arr = {"12:30:15", "12:30:45"};
        int result = minDifference(arr);
        System.out.println(result); // Expected: 30
    }
}

/*
Time Complexity: O(N + 86400) = O(N), where N is number of time strings
Space Complexity: O(1), using a fixed-size boolean array of 86400
*/
