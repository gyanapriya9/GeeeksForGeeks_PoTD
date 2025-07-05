// PoTD 06 July 2025
// Maximum Sum Combination

// Layman Explanation:
// You're given two arrays of equal size. You can take one number from the first array 
// and one from the second, and add them to form a sum. You're allowed to make k such
// combinations — your goal is to find the **k largest sums** possible.
// Instead of calculating all possible pairs (which would be too slow), we use a max-heap
// to **greedily pick the best pairs** starting from the biggest numbers, and move step by step
// to smaller combinations using indices.

import java.util.*;

class Solution {

    // Helper function to reverse an array
    public void reverse(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left++] = arr[right];
            arr[right--] = temp;
        }
    }

    public ArrayList<Integer> topKSumPairs(int[] a, int[] b, int k) {
        int n = a.length;

        // Step 1: Sort both arrays in descending order
        Arrays.sort(a);
        Arrays.sort(b);
        reverse(a);
        reverse(b);

        // Step 2: Use max-heap to keep top sums
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((x, y) -> y[0] - x[0]);
        HashSet<String> visited = new HashSet<>();

        // Step 3: Push the largest possible pair initially (first of a + first of b)
        maxHeap.offer(new int[]{a[0] + b[0], 0, 0});
        visited.add("0#0");

        ArrayList<Integer> result = new ArrayList<>();

        // Step 4: Extract top k elements
        while (result.size() < k && !maxHeap.isEmpty()) {
            int[] current = maxHeap.poll();
            int sum = current[0], i = current[1], j = current[2];
            result.add(sum); // Store this sum in result

            // Step 5: Push next possible pair from a
            if (i + 1 < n && !visited.contains((i + 1) + "#" + j)) {
                maxHeap.offer(new int[]{a[i + 1] + b[j], i + 1, j});
                visited.add((i + 1) + "#" + j);
            }

            // Step 6: Push next possible pair from b
            if (j + 1 < n && !visited.contains(i + "#" + (j + 1))) {
                maxHeap.offer(new int[]{a[i] + b[j + 1], i, j + 1});
                visited.add(i + "#" + (j + 1));
            }
        }

        return result;
    }
}
