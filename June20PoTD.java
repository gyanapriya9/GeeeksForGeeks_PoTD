// 2025-06-20 PoTD

// 🧠 Problem Summary in Layman Terms:
// We have a bunch of balls with numbers on them.
// We want to divide them into groups of exactly `k` balls each.
// But not just that – the numbers in each group must be consecutive (like 1,2 or 10,11).
// We need to check if it’s possible to make such groups using all the balls.

import java.util.*;

class Solution {

    public boolean validgroup(int[] arr, int k) {

        // ❌ If total balls can't be evenly divided into groups of k, return false early
        if (arr.length % k != 0) return false;

        // ✅ TreeMap keeps keys sorted – perfect for checking consecutive numbers
        TreeMap<Integer, Integer> frequencyMap = new TreeMap<>();

        // Step 1: Count how many times each number appears
        for (int num : arr) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Try forming groups of consecutive numbers
        while (!frequencyMap.isEmpty()) {

            // Always start from the smallest available number
            int start = frequencyMap.firstKey();
            int minCount = frequencyMap.get(start); // Number of groups we must form starting from `start`

            // Try to form one group of size `k` starting from `start`
            for (int i = 0; i < k; i++) {
                int current = start + i;

                // If any number in the group is missing, grouping fails
                if (!frequencyMap.containsKey(current)) return false;

                int updatedCount = frequencyMap.get(current) - minCount;

                // If count becomes negative, grouping is invalid
                if (updatedCount < 0) return false;

                // If all instances of this number are used up, remove it
                if (updatedCount == 0) {
                    frequencyMap.remove(current);
                } else {
                    frequencyMap.put(current, updatedCount);
                }
            }
        }

        // ✅ If we successfully formed all groups, return true
        return true;
    }
}
