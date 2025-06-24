// 🔁 PoTD 23 June 2025
// 🔍 Problem: Merge Two Sorted Linked Lists

// 🧠 Layman Explanation:
// You are given two chains (linked lists) of boxes sorted by numbers inside.
// You need to combine both chains into a single sorted chain — just like merging two sorted arrays but in linked list form.
// You are not allowed to create new nodes, just re-arrange the existing ones.

class Solution {
    // Node class representing each element of the linked list
    static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Function to merge two sorted linked lists
    public Node sortedMerge(Node head1, Node head2) {
        // Handle base cases: if one of the lists is empty, return the other
        if (head1 == null) return head2;
        if (head2 == null) return head1;

        // Dummy node to ease handling the merged list
        Node dummy = new Node(-1);  // temporary starter node
        Node tail = dummy;          // pointer to build the merged list

        // Traverse both lists and compare node values one by one
        while (head1 != null && head2 != null) {
            if (head1.data <= head2.data) {
                // If head1 has a smaller or equal value, append it
                tail.next = head1;
                head1 = head1.next; // move head1 to next node
            } else {
                // If head2 has a smaller value, append it
                tail.next = head2;
                head2 = head2.next; // move head2 to next node
            }
            // Move tail to the last added node
            tail = tail.next;
        }

        // One of the lists might still have remaining nodes
        // Append them directly since both lists were already sorted
        if (head1 != null) {
            tail.next = head1;
        } else {
            tail.next = head2;
        }

        // Return the merged list (skip the dummy node)
        return dummy.next;
    }
}
