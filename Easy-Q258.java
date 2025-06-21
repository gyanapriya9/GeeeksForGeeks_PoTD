// LeetCode 258: Add Digits — Easy

class Solution {
  public int addDigits(int num) {
    return num == 0 ? 0 : 1 + (num - 1) % 9;
  }

  /*
   Flashcard Summary:
   ------------------
   Problem:
     - Given a number, repeatedly add its digits until a single-digit result is reached.

   Example:
     - Input: 38
       3 + 8 = 11 → 1 + 1 = 2
       Output: 2

   Concept:
     - This is based on a mathematical property called **Digital Root**.
     - For base-10 numbers:
         If num == 0 → return 0
         Else → return 1 + (num - 1) % 9

   Why it works:
     - The digital root of a non-zero number in base-10 is 1 + ((num - 1) mod 9).
     - This gives O(1) time complexity without loops or recursion.

   Time Complexity: O(1)
   Space Complexity: O(1)
  */
}
