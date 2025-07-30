// Today Date PoTD : 30th July 2025

/*
 * Question:
 * Given an array of integers, count the number of subarrays whose sum is equal to a given number k.
 * 
 * Flashcard Format:
 * - Problem Type: Subarray with Target Sum (Prefix Sum + HashMap)
 * - Key Idea: Use cumulative prefix sums and store frequencies in a HashMap
 * - Why? Helps in identifying how many subarrays (ending at current index) have the desired sum
 */

class Solution {
    public int cntSubarrays(int[] arr, int k) {
        // HashMap to store frequency of prefix sums encountered so far
        Map<Integer, Integer> prefixSums = new HashMap<>();

        int res = 0;         // To store result - number of valid subarrays
        int currSum = 0;     // Running prefix sum

        for (int i = 0; i < arr.length; i++) {
            currSum += arr[i];  // Add current element to prefix sum

            // Case 1: If current prefix sum itself equals k, count this subarray
            if (currSum == k) res++;

            // Case 2: Check if there is a prefix sum that if removed gives us k
            if (prefixSums.containsKey(currSum - k)) {
                res += prefixSums.get(currSum - k);
            }

            // Add or update the frequency of current prefix sum
            prefixSums.put(currSum, prefixSums.getOrDefault(currSum, 0) + 1);
        }

        return res;
    }
}

/*
 * Time Complexity: O(n)
 * - We traverse the array once and perform constant-time operations using the HashMap.
 * 
 * Space Complexity: O(n)
 * - In the worst case, we store up to n unique prefix sums in the HashMap.
 */
