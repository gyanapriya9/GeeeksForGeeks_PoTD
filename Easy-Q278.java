// LeetCode 278: First Bad Version — Easy
// You are given a boolean API isBadVersion(version)

public class Solution extends VersionControl {
  public int firstBadVersion(int n) {
    int l = 1;
    int r = n;

    while (l < r) {
      int m = l + (r - l) / 2; // Prevents integer overflow
      if (isBadVersion(m)) // m might be the first bad version
        r = m;
      else
        l = m + 1;
    }

    return l; // l == r is the first bad version
  }

  /*
   Flashcard Summary:
   ------------------
   Problem:
     - Find the first bad version using the least number of calls to isBadVersion(version).

   Examples:
     - Input: n = 5, bad = 4
       Output: 4
       Calls: isBadVersion(3) → false, isBadVersion(5) → true, isBadVersion(4) → true

   Concept:
     - Use Binary Search.
     - If mid is bad, search left (r = mid).
     - If mid is good, search right (l = mid + 1).

   Key Insight:
     - Once we find a bad version, any version after it is guaranteed to be bad.
     - The search space shrinks in half each time → O(log n)

   Time Complexity: O(log n)
   Space Complexity: O(1)
  */
}
