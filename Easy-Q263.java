// LeetCode 263: Ugly Number — Easy

class Solution {
  public boolean isUgly(int n) {
    if (n <= 0)
      return false;

    for (int prime : new int[]{2, 3, 5})
      while (n % prime == 0)
        n /= prime;

    return n == 1;
  }

  /*
   Flashcard Summary:
   ------------------
   Problem:
     - An ugly number is a positive number whose prime factors are only 2, 3, or 5.
     - Return true if the input number is ugly, false otherwise.

   Examples:
     - Input: 6 → Output: true → 6 = 2 × 3
     - Input: 1 → Output: true (1 is considered ugly)
     - Input: 14 → Output: false (has prime factor 7)

   Approach:
     - If n is 0 or negative → return false
     - Divide n by 2, 3, and 5 repeatedly until not divisible
     - If n becomes 1 → it's ugly
     - Else → it contains a different prime factor

   Time Complexity: O(log n)
   Space Complexity: O(1)
  */
}
