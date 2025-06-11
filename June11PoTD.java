// June 11, 2025 – GFG PoTD 2.0

// 🧩 Question: Remove the Balls
/*
Given:
- Two arrays `color[]` and `radius[]`, each representing properties of balls.

Task:
- Remove all **consecutive** pairs of balls that have the **same color and radius**.
- Repeat the process until no more such adjacent identical balls exist.
- Return the number of balls left after all such removals.

Examples:
Input: color = [2, 3, 5], radius = [3, 3, 5] ➝ Output: 3 (no removal)
Input: color = [2, 2, 5], radius = [3, 3, 5] ➝ Output: 1 (2 balls removed)
*/

// ✅ Stack-Based Solution:
/*
1. Use a stack to track indices of balls that have not been removed.
2. For each ball:
   - If the top of the stack has the same color and radius ➝ Pop (remove the pair).
   - Else ➝ Push the current index to stack.
3. At the end, the size of the stack = number of remaining balls.
*/

class Solution {
    public int findLength(int[] color, int[] radius) {
        int n = color.length;

        // Stack to store indices of remaining balls
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {

            // Check if current ball can be paired with top of stack
            if (!st.empty() &&
                color[i] == color[st.peek()] &&
                radius[i] == radius[st.peek()]) {

                st.pop(); // Remove both balls
            } else {
                st.push(i); // Keep this ball
            }
        }

        // Remaining balls count
        return st.size();
    }
}
