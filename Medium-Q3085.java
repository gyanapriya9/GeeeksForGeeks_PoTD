// LeetCode 3085: Minimum Deletions to Make String K-Special — Medium

class Solution {
  public int minimumDeletions(String word, int k) {
    int ans = Integer.MAX_VALUE;
    int[] count = new int[26];

    // 📊 Step 1: Count frequencies of all characters
    for (char c : word.toCharArray())
      count[c - 'a']++;

    // 💡 Try each frequency as possible minFreq for the K-special condition
    for (int minFreq : count) {
      if (minFreq == 0) continue; // Skip unused characters

      int deletions = 0;

      for (int freq : count) {
        if (freq == 0) continue;

        if (freq < minFreq)
          deletions += freq; //  Delete low-frequency chars (not reaching minFreq)
        else
          deletions += Math.max(0, freq - (minFreq + k)); //  Delete excess chars
      }

      ans = Math.min(ans, deletions); //  Track minimum deletions needed
    }

    return ans;
  }

  /*
    Flashcard Summary:
   -----------------------
    Problem: Make the string k-special:
      |freq(i) - freq(j)| <= k for all characters.
   
    Example:
      word = "aabcaba", k = 0 → Output: 3
    Strategy:
     - Count the frequency of each character.
     - Try every non-zero frequency as the *minimum base frequency*.
     - Delete all characters lower than base.
     - For characters higher than base + k, delete the excess.
     - Track minimum total deletions.

    Time Complexity: O(26^2) = O(1) → because alphabet is fixed
    Space Complexity: O(26) → constant for character count
    Handles large word sizes efficiently.
  */
}
