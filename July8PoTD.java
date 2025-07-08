// PoTD 08 July 2025
//  Next Element with Greater Frequency

//  Layman Explanation:
// You're given an array. For each element, you want to find the nearest number to its right 
// that appears **more frequently** in the array. If no such number exists, return -1.
// We'll use a frequency map to count occurrences, and a stack to efficiently track indices
// for which the answer hasn't been found yet. As we move through the array, if the current 
// element has a **higher frequency** than the one at the top of the stack, it's the answer 
// for that index.

import java.util.*;

class Solution {

    public ArrayList<Integer> findGreater(int[] arr) {
        int n = arr.length;

        // Step 1: Count frequency of each element
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // Step 2: Prepare result array with -1 as default
        int[] res = new int[n];
        Arrays.fill(res, -1);

        // Step 3: Use stack to keep track of elements whose answer is not yet found
        Stack<Integer> stack = new Stack<>();

        // Step 4: Traverse the array
        for (int i = 0; i < n; i++) {
            // While current element has higher frequency than the element at the top of the stack
            while (!stack.isEmpty() && freq.get(arr[i]) > freq.get(arr[stack.peek()])) {
                int idx = stack.pop();         // Resolve the top index
                res[idx] = arr[i];             // arr[i] is the next greater frequency element
            }
            stack.push(i);                     // Push current index for future comparisons
        }

        // Step 5: Convert result array to ArrayList
        ArrayList<Integer> resultList = new ArrayList<>();
        for (int value : res) {
            resultList.add(value);
        }

        return resultList;
    }
}
