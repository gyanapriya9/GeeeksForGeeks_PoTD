// Question: Nine Divisors
// Date: 17-07-2025

// Flashcard Summary:
// You are given a number `n`. You must count how many numbers ≤ n have exactly **9 divisors**.
// A number can have exactly 9 divisors if it is of the form:
//    1. p^8             → where p is a prime number
//    2. p^2 * q^2       → where p and q are distinct prime numbers

// Approach:
// - Use Sieve of Eratosthenes to precompute smallest prime factors up to sqrt(n)
// - Then, check all numbers up to sqrt(n) if they match either of the two patterns

class Solution {
    public static int countNumbers(int n) {
        int count = 0;

        // Calculate the square root of n as upper bound for sieving and checking
        int limit = (int) Math.sqrt(n);

        // Initialize prime array where prime[i] = smallest prime factor of i
        int[] prime = new int[limit + 1];
        for (int i = 1; i <= limit; i++) {
            prime[i] = i;
        }

        // Sieve of Eratosthenes to fill smallest prime factor (SPF)
        for (int i = 2; i * i <= limit; i++) {
            if (prime[i] == i) {  // i is a prime number
                for (int j = i * i; j <= limit; j += i) {
                    if (prime[j] == j) {
                        prime[j] = i;  // Mark SPF
                    }
                }
            }
        }

        // Iterate through all numbers up to sqrt(n)
        for (int i = 2; i <= limit; i++) {
            int p = prime[i];              // smallest prime factor of i
            int q = prime[i / p];          // smallest prime factor of the quotient

            // Case 1: Number is of the form p^2 * q^2 (p ≠ q)
            // Example: 36 = 2^2 * 3^2
            if (p * q == i && p != q && q != 1) {
                count++;
            }

            // Case 2: Number is of the form p^8
            // Example: 256 = 2^8
            else if (prime[i] == i && Math.pow(i, 8) <= n) {
                count++;
            }
        }

        return count;
    }
}
