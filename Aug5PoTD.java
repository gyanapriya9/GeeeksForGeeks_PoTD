// PoTD Date: 5th Aug 2025
// Problem: Palindrome Sentence
// Task: Check if a given sentence is a palindrome after removing non-alphanumeric characters and ignoring case

/*
Flashcard-style Explanation:
-----------------------------------
What is required?
- Given a string s, determine if it reads the same forward and backward
  after:
  1. Removing all non-alphanumeric characters
  2. Converting all characters to lowercase

Approach:
- Use two pointers: one starting from the beginning (i), one from the end (j)
- Skip any characters that are not letters or digits
- Compare the valid characters at both pointers in a case-insensitive manner
- If all matching characters are equal from both ends, it's a palindrome
*/

// Java implementation
class Solution {
    public boolean isPalinSent(String s) {
        int i = 0, j = s.length() - 1; // Initialize pointers at start and end

        // Loop until pointers meet or cross
        while (i < j) {

            // Skip non-alphanumeric character from the left side
            if (!Character.isLetterOrDigit(s.charAt(i))) {
                i++; // Move left pointer forward
            }

            // Skip non-alphanumeric character from the right side
            else if (!Character.isLetterOrDigit(s.charAt(j))) {
                j--; // Move right pointer backward
            }

            // If both characters are valid, compare them in lowercase
            else if (Character.toLowerCase(s.charAt(i)) ==
                     Character.toLowerCase(s.charAt(j))) {
                i++; // Move both pointers inward if characters match
                j--;
            }

            // If characters don't match, it's not a palindrome
            else {
                return false;
            }
        }

        // If loop completes, the sentence is a palindrome
        return true;
    }
}

/*
Time Complexity:
- O(n), where n is the length of the string
  - Each character is processed at most once from either end

Space Complexity:
- O(1), constant space used (no additional space for string manipulation)
*/
