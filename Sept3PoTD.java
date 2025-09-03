/*
    PoTD Date: 3rd Sept 2025

    Quick Flashcard:
    ----------------
    Problem:
    - You are given the head of a doubly linked list.
    - Reverse the doubly linked list and return the new head.

    Example:
    Input:  3 <-> 4 <-> 5
    Output: 5 <-> 4 <-> 3

    Approach:
    - Traverse through the list.
    - For each node, swap its "next" and "prev" pointers.
    - Move to the next node (which is actually prev after swapping).
    - At the end, update head to the new starting node.
*/

class Solution {
    public Node reverse(Node head) {
        // Base case: If list is empty or has only one node → no reversal needed
        if (head == null || head.next == null) {
            return head;
        }

        Node currNode = head;   // Current node for traversal
        Node prevNode = null;   // To keep track of swapped links

        // Traverse the entire doubly linked list
        while (currNode != null) {

            // Swap pointers of current node
            prevNode = currNode.prev;     // Store current prev
            currNode.prev = currNode.next; // Make prev point to next
            currNode.next = prevNode;      // Make next point to prev

            // Move to "next" node in original list
            // After swap, it's stored in currNode.prev
            currNode = currNode.prev;
        }

        // At the end, prevNode points to old head
        // The new head is prevNode.prev after final swap
        head = prevNode.prev;

        return head;
    }
}

/*
    Time Complexity:
    - O(N), where N = number of nodes (each node visited once).

    Space Complexity:
    - O(1), no extra space used, only pointers.
*/
