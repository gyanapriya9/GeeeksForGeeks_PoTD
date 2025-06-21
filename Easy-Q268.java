// LeetCode 268: Missing Number — Easy

class Solution {
  public int missingNumber(int[] nums) {
    int ans = nums.length; // Start from n since range is [0, n]

    for (int i = 0; i < nums.length; ++i)
      ans ^= i ^ nums[i]; // XOR with both index and element

    return ans;
  }

  /*
   Flashcard Summary:
   ------------------
   Problem:
     - Given n distinct numbers from [0, n], find the missing number.

   Examples:
     - Input: [3, 0, 1] → Output: 2
     - Input: [0, 1] → Output: 2
     - Input: [9,6,4,2,3,5,7,0,1] → Output: 8

   Concept:
     - Use XOR to cancel out all matching index and value pairs.
     - XOR of all indices from 0 to n and all elements in nums will leave the missing number.

   Why XOR Works:
     - a ^ a = 0, and 0 ^ b = b
     - So, (0^1^2^…^n) ^ (nums[0]^nums[1]^…^nums[n-1]) = missing number

   Time Complexity: O(n)
   Space Complexity: O(1)

   Alternate Approach (for clarity):
     - Sum formula: missing = n*(n+1)/2 - sum(nums)
  */
}
