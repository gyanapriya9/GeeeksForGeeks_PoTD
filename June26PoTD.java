// 📅 PoTD 25 June 2025
// 🧠 Game with String
// 💬 Layman’s Explanation:
// You're given a string made of lowercase letters and a number `k`. You can remove `k` characters (any of your choice). After removing, calculate the "value" of the string, where the value is:
// square of count of each character → sum of all these.
// Example: "abbccc" after removing 2 characters can become "abc", giving 1² + 1² + 1² = 3 → which is lowest possible.

class Solution {
    public int minValue(String s, int k) {
        // Frequency array to count each lowercase letter
        int[] charFreq = new int[26];

        // Fill up the frequency array based on given string
        for (char ch : s.toCharArray()) {
            charFreq[ch - 'a']++;
        }

        // Max-heap (PriorityQueue in reverse order) to always get the character with highest frequency
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Add all non-zero frequencies to the heap
        for (int count : charFreq) {
            if (count > 0) {
                maxHeap.add(count);
            }
        }

        // Perform k removals
        while (k > 0 && !maxHeap.isEmpty()) {
            int top = maxHeap.poll(); // Get character with highest frequency
            top--; // Remove one occurrence of that character
            k--; // Count one removal
            if (top > 0) {
                maxHeap.add(top); // Put the updated frequency back
            }
        }

        int result = 0;

        // After all removals, calculate the final value
        while (!maxHeap.isEmpty()) {
            int val = maxHeap.poll();
            result += val * val;
        }

        return result;
    }
}
