import java.util.HashMap;

class Solution {
    public int sumOfModes(int[] arr, int k) {
        int n = arr.length;
        int sum = 0;

        // Iterate over all subarrays of size k
        for (int i = 0; i <= n - k; i++) {
            HashMap<Integer, Integer> freq = new HashMap<>();
            int mode = Integer.MAX_VALUE;
            int maxFreq = 0;

            // Traverse the current window and build frequency map
            for (int j = i; j < i + k; j++) {
                int val = arr[j];
                freq.put(val, freq.getOrDefault(val, 0) + 1);

                if (freq.get(val) > maxFreq || (freq.get(val) == maxFreq && val < mode)) {
                    maxFreq = freq.get(val);
                    mode = val;
                }
            }

            sum += mode;
        }

        return sum;
    }
}
