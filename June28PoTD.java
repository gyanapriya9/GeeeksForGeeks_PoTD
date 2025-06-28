// PoTD 28 June 2025
// Counting Elements in Two Arrays

// Explanation:
// We are given two arrays `a[]` and `b[]`. For each number in `a`, we need to count
// how many numbers in `b` are less than or equal to it.
// Instead of checking each element one by one (which would be slow for large arrays),
// we use frequency counting + prefix sum to speed it up.

// Approach: Use a frequency array to count elements in `b`, then build a prefix sum
// so that for any number x, we instantly know how many numbers in `b` are ≤ x.

class Solution {
    public static ArrayList<Integer> countLessEq(int[] a, int[] b) {
        int n = a.length;
        int m = b.length;

        // Initialize result array to store answers for each a[i]
        ArrayList<Integer> result = new ArrayList<>(Collections.nCopies(n, 0));

        // Create a frequency array for values in b[]
        int[] freq = new int[100001]; // 0 to 100000 allowed

        // Count occurrences of each number in b
        for (int value : b) {
            freq[value]++;
        }

        // Convert frequency array into prefix sum array
        // So that freq[i] tells how many numbers are ≤ i
        for (int i = 1; i < freq.length; i++) {
            freq[i] += freq[i - 1];
        }

        // For every element in a[], get the count of numbers in b[] ≤ a[i]
        for (int i = 0; i < n; i++) {
            result.set(i, freq[a[i]]);
        }

        return result;
    }
}
