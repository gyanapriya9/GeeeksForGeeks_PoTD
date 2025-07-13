

// Flashcard:
// Given an array of positive integers, we must calculate the sum of elements NOT included in 
// the Longest Increasing Subsequence (LIS).
// -> Step 1: Find the LIS using a TreeMap to maintain optimal LIS values.
// -> Step 2: Total sum of all elements - LIS sum = Required Answer

class Solution {

    // TreeMap to maintain LIS info: key = value in array,
    // value = [length of LIS ending here, sum of LIS ending here]
    public TreeMap<Integer, int[]> mp = new TreeMap<>();

    // Method to insert each value and update the map to maintain optimal LIS
    public void insert(int val) {
        // Get first entry >= current value
        Map.Entry<Integer, int[]> it = mp.ceilingEntry(val);

        // Default LIS for new value: length 1, sum as itself
        int len = 1;
        int sum = val;

        // Check if any smaller value exists to extend the LIS
        Map.Entry<Integer, int[]> prev = mp.lowerEntry(val);
        if (prev != null) {
            // If exists, update len and sum for LIS ending at current val
            len = prev.getValue()[0] + 1;
            sum = prev.getValue()[1] + val;
        }

        // Remove dominated entries with lesser or equal length but higher or equal keys
        List<Integer> toerase = new ArrayList<>();
        while (it != null) {
            if (it.getValue()[0] > len) break;
            toerase.add(it.getKey());
            it = mp.higherEntry(it.getKey());
        }

        for (int key : toerase) {
            mp.remove(key);
        }

        // Insert current value into map with its LIS length and LIS sum
        mp.put(val, new int[] {len, sum});
    }

    public int nonLisMaxSum(int[] arr) {
        mp.clear();  // Reset TreeMap before processing new array

        // Insert each element to calculate LIS
        for (int val : arr) {
            insert(val);
        }

        // Get the LIS with maximum length (last entry)
        int[] last = mp.lastEntry().getValue();

        // Total sum of all elements in array
        int totalSum = Arrays.stream(arr).sum();

        // LIS sum is stored in the value part
        int lisSum = last[1];

        // Return the sum of elements not in LIS
        return totalSum - lisSum;
    }
}
