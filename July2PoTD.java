// PoTD 02 July 2025
// Longest Subarray with At Most Two Distinct Integers

// Layman's Explanation:
// You're given an array of positive integers. 
// Your task is to find the length of the longest continuous subarray 
// that contains at most two different numbers.
// We'll use a sliding window approach to maintain this subarray 
// while keeping track of how many distinct numbers are inside it.

class Solution {
    public int totalElements(int[] arr) {
        // Map to store the frequency of each element in the current window
        Map<Integer, Integer> freqMap = new HashMap<>();

        int left = 0, right = 0;
        int maxLen = 0;

        // Expand the window using the right pointer
        while (right < arr.length) {
            int current = arr[right];
            freqMap.put(current, freqMap.getOrDefault(current, 0) + 1);

            // If there are more than two distinct numbers, shrink the window from the left
            while (freqMap.size() > 2) {
                int removeNum = arr[left];
                freqMap.put(removeNum, freqMap.get(removeNum) - 1);
                if (freqMap.get(removeNum) == 0) {
                    freqMap.remove(removeNum);
                }
                left++; // Move left pointer to reduce distinct elements
            }

            // Update the maximum length found so far
            maxLen = Math.max(maxLen, right - left + 1);
            right++; // Expand window to the right
        }

        return maxLen; // Return the length of the longest valid subarray
    }
}
