// Today’s PoTD: 21st July 2025
// Problem: Count the Coprimes
// Given an array `arr[]`, count the number of unordered index pairs (i, j) such that 0 ≤ i < j < n 
// and gcd(arr[i], arr[j]) == 1

// Flashcard:
// - We are to count how many pairs in the array are co-prime.
// - Brute force O(n^2) is too slow for n up to 10^4.
// - We use a number-theoretic approach involving:
//   1. Möbius Function (μ) for Inclusion-Exclusion
//   2. Frequency array to count occurrences
//   3. Preprocessing to count how many numbers are divisible by a value 'k'
//   4. Final count uses Möbius and Inclusion-Exclusion to count co-prime pairs

class Solution {

    // Computes the Möbius function μ for all numbers up to 'n'
    void computeMobius(int n, int[] mu) {
        int[] is_prime = new int[n + 1];
        Arrays.fill(is_prime, 1);
        mu[0] = 0;
        mu[1] = 1;

        // Sieve-based Möbius function computation
        for (int i = 2; i <= n; ++i) {
            if (is_prime[i] == 1) {
                for (int j = i; j <= n; j += i) {
                    mu[j] *= -1;           // Flip sign for each prime factor
                    is_prime[j] = 0;       // Mark composite
                }
                // Eliminate numbers that are not square-free
                for (int j = i * i; j <= n; j += i * i) {
                    mu[j] = 0;             // Not square-free => Möbius is 0
                }
            }
        }
    }

    // Builds a frequency array where freq[x] = count of x in arr[]
    void buildFre(int[] arr, int[] freq) {
        for (int x : arr) freq[x]++;
    }

    // For each number k, count how many elements in arr[] are divisible by k
    void computeDivCnt(int maxVal, int[] freq, int[] d) {
        for (int k = 1; k <= maxVal; ++k) {
            for (int j = k; j <= maxVal; j += k) {
                d[k] += freq[j];  // Sum counts of all multiples of k
            }
        }
    }

    // Main function that uses Möbius and inclusion-exclusion to count co-prime pairs
    int cntCoprime(int[] arr) {
        int maxVal = Arrays.stream(arr).max().getAsInt();  // Find maximum element in arr
        int[] freq = new int[maxVal + 1];  // freq[x] = how many times x appears
        int[] d = new int[maxVal + 1];     // d[k] = how many numbers divisible by k
        int[] mu = new int[maxVal + 1];    // Möbius values

        Arrays.fill(mu, 1);

        buildFre(arr, freq);               // Build frequency array
        computeDivCnt(maxVal, freq, d);   // Compute divisor count
        computeMobius(maxVal, mu);        // Compute Möbius function

        int result = 0;

        // Using Inclusion-Exclusion with Möbius function to count valid pairs
        for (int k = 1; k <= maxVal; ++k) {
            if (mu[k] == 0 || d[k] < 2) continue;  // Skip if not square-free or less than 2 elements divisible
            int pairs = (int)((long)d[k] * (d[k] - 1) / 2);  // Count of unordered pairs from d[k] elements
            result += mu[k] * pairs;  // Add or subtract depending on Möbius value
        }

        return result;
    }
}

/*
Time Complexity:
- O(n log n) due to divisor counting and Möbius preprocessing
- Efficient for n ≤ 10^4

Space Complexity:
- O(max(arr[i])) for frequency, divisor, and Möbius arrays
*/
