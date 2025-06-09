// June 9, 2025 – GFG PoTD 2.0

// 🧩 Question: BST with Dead End
/*
Given:
- A Binary Search Tree (BST) with only **unique positive integers > 0**.

Task:
- Determine if the BST contains a **dead end**.

Definition:
- A **dead end** is a **leaf node** such that:
  - No further insertion is possible in the subtree starting from that node,
  - While maintaining BST properties and keeping values > 0.

Example:
Input: [8, 5, 9, 2, 7, N, N, 1]
Dead End: Node 1 (no number can be inserted at or below it) ✅
*/

// ✅ Approach: DFS with range tracking
/*
1. For every node, track the **valid insertion range**: [min, max].
2. When reaching a **leaf**, check:
   - If min == max, it means no valid number can be inserted here.
3. Apply DFS to left and right subtrees with updated ranges:
   - Left: [min, node.data - 1]
   - Right: [node.data + 1, max]
4. Base condition: if root is null, return false.
5. If any leaf leads to min == max, return true (dead end found).
*/

class Solution {

    // 🔹 Helper DFS method with range [mini, maxi]
    private boolean dfs(Node root, int mini, int maxi) {

        // Base case: empty node
        if (root == null) return false;

        // Dead end check: leaf node with no space to insert
        if (root.left == null && root.right == null && mini == maxi) {
            return true;
        }

        // Recurse left and right with narrowed ranges
        return dfs(root.left, mini, root.data - 1) ||
               dfs(root.right, root.data + 1, maxi);
    }

    // 🔹 Entry function: start DFS with full valid range
    public boolean isDeadEnd(Node root) {
        return dfs(root, 1, Integer.MAX_VALUE); // BST starts with only positive integers > 0
    }
}
