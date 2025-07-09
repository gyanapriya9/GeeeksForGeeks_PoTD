import java.util.*;

class Solution {
    public int sumSubMins(int[] arr) {
        int n = arr.length;
        long result = 0;
        int mod = (int) 1e9 + 7;

        // Stacks for previous and next less element index calculation
        Stack<Integer> prevStack = new Stack<>();
        Stack<Integer> nextStack = new Stack<>();

        int[] prevLess = new int[n];  // Stores number of elements before i where arr[i] is minimum
        int[] nextLess = new int[n];  // Stores number of elements after i where arr[i] is minimum

        // --------- Calculate prevLess[i] for all i ----------
        for (int i = 0; i < n; i++) {
            // Pop until we find a smaller element on the left
            while (!prevStack.isEmpty() && arr[prevStack.peek()] > arr[i]) {
                prevStack.pop();
            }

            // If stack is empty, all elements to the left are greater
            // So distance = i + 1 (0 to i inclusive)
            // Else, distance = i - prevStack.peek()
            prevLess[i] = prevStack.isEmpty() ? i + 1 : i - prevStack.peek();

            // Push current index to stack for next iterations
            prevStack.push(i);
        }

        // --------- Calculate nextLess[i] for all i ----------
        for (int i = n - 1; i >= 0; i--) {
            // Pop until we find a strictly smaller element on the right
            // Note: >= ensures duplicates also break here
            while (!nextStack.isEmpty() && arr[nextStack.peek()] >= arr[i]) {
                nextStack.pop();
            }

            // If stack is empty, no smaller element on the right
            // So distance = n - i (i to end)
            // Else, distance = nextStack.peek() - i
            nextLess[i] = nextStack.isEmpty() ? n - i : nextStack.peek() - i;

            // Push current index for further comparison
            nextStack.push(i);
        }

        // --------- Calculate total contribution ---------
        for (int i = 0; i < n; i++) {
            // Contribution of arr[i] is: value * count of subarrays where it's minimum
            long contrib = (long) arr[i] * prevLess[i] * nextLess[i];

            // Add contribution to result modulo 10^9 + 7
            result = (result + contrib) % mod;
        }

        // Return final result as int
        return (int) result;
    }
}
