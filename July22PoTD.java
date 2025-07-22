/*
 * Today Date PoTD : 22nd July 2025
 *
 * Problem: Smallest Positive Missing Number
 * 
 * Flashcard:
 * - Task: Find the smallest positive number missing from an unsorted array (can include negatives).
 * - Input: An array of integers (may include negative values and duplicates).
 * - Output: The smallest positive integer (starting from 1) that is not present in the array.
 * - Constraints:
 *   - 1 ≤ arr.length ≤ 10^5
 *   - -10^6 ≤ arr[i] ≤ 10^6
 *
 * Approach:
 * - Use index mapping (placing numbers at correct indices) to rearrange the array in-place.
 * - Any number in range 1 to n should be placed at index arr[i] - 1.
 * - After rearrangement, traverse to find the first index i such that arr[i] != i + 1.
 * - That index + 1 will be the missing number.
 * - If all positions are correct, then answer is n + 1.
 */

class Solution {
    public int missingNumber(int[] arr) {
        int n = arr.length;

        // Rearranging the elements in their correct positions
        for (int i = 0; i < n; i++) {
            // Ensure current number is in the valid range and not already at correct position
            while (arr[i] >= 1 && arr[i] <= n && arr[i] != arr[arr[i] - 1]) {
                // Swap arr[i] with arr[arr[i] - 1] to move it to the correct index
                int temp = arr[i];
                arr[i] = arr[temp - 1];
                arr[temp - 1] = temp;
            }
        }

        // After rearrangement, find the first index where arr[i] != i + 1
        for (int i = 0; i < n; i++) {
            if (arr[i] != i + 1) {
                return i + 1;
            }
        }

        // If all numbers from 1 to n are in place, return n + 1
        return n + 1;
    }
}

/*
 * Time Complexity: O(n) — Each number is swapped at most once.
 * Space Complexity: O(1) — In-place rearrangement without extra space.
 */
