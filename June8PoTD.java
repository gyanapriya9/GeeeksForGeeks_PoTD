// June 8, 2025 – GFG PoTD 2.0

// 🧩 Question: Sum-string
/*
Given:
- A string `s` of digits.
Task:
- Check if it can be split into **more than two non-empty** substrings such that:
  1. The last substring is the sum of the two before it (as integers).
  2. This rule continues recursively.
  3. No number in the split can have leading zeros (unless it's exactly '0').

Example:
Input: "12243660"
Split: ["12", "24", "36", "60"]
=> 12 + 24 = 36, 24 + 36 = 60 ✅ Valid sum-string
*/

// ✅ Approach Explanation:
/*
1. Try all possible combinations of the first and second numbers in the string.
2. For each such pair:
   - Add them (simulate integer addition via strings).
   - Compare the result with the next segment of the string.
   - If it matches, recursively check the next set: (second, sum).
   - If all segments match this rule till the end, return true.
3. Leading zeros in any segment => invalid.
4. Return true if any such valid decomposition is found.
*/

class Solution {

    // 🔹 Helper method to add two numeric strings (like doing column-wise addition)
    private String addStrings(String num1, String num2) {

        if (num1.length() < num2.length()) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }

        int len1 = num1.length();
        int len2 = num2.length();
        String sum = "";
        int carry = 0;

        // Add common digits from right to left
        for (int i = 0; i < len2; i++) {
            int d1 = num1.charAt(len1 - 1 - i) - '0';
            int d2 = num2.charAt(len2 - 1 - i) - '0';
            int digit = (d1 + d2 + carry) % 10;
            carry = (d1 + d2 + carry) / 10;
            sum = (char)(digit + '0') + sum;
        }

        // Add remaining digits from the longer number
        for (int i = len2; i < len1; i++) {
            int d = num1.charAt(len1 - 1 - i) - '0';
            int digit = (d + carry) % 10;
            carry = (d + carry) / 10;
            sum = (char)(digit + '0') + sum;
        }

        // Final carry
        if (carry > 0) {
            sum = (char)(carry + '0') + sum;
        }

        return sum;
    }

    // 🔹 Recursively checks if valid sum-sequence continues from index 'start'
    private boolean checkSequence(String s, int start, int len1, int len2) {
        String part1 = s.substring(start, start + len1);
        String part2 = s.substring(start + len1, start + len1 + len2);

        // Leading zero check
        if ((part1.length() > 1 && part1.charAt(0) == '0') ||
            (part2.length() > 1 && part2.charAt(0) == '0')) {
            return false;
        }

        String expectedSum = addStrings(part1, part2);
        int sumLen = expectedSum.length();

        // Check if next segment exists
        if (start + len1 + len2 + sumLen > s.length()) {
            return false;
        }

        String nextPart = s.substring(start + len1 + len2, start + len1 + len2 + sumLen);

        // Leading zero check
        if (nextPart.length() > 1 && nextPart.charAt(0) == '0') {
            return false;
        }

        // Check if sum matches next part
        if (expectedSum.equals(nextPart)) {
            // If end reached => valid sum-string
            if (start + len1 + len2 + sumLen == s.length()) {
                return true;
            }

            // Recurse with next pair (part2, expectedSum)
            return checkSequence(s, start + len1, len2, sumLen);
        }

        return false; // No match
    }

    // 🔹 Main function to check for any valid initial split
    public boolean isSumString(String s) {
        int n = s.length();

        // Try every possible first and second number lengths
        for (int len1 = 1; len1 < n; len1++) {
            for (int len2 = 1; len1 + len2 < n; len2++) {
                if (checkSequence(s, 0, len1, len2)) {
                    return true;
                }
            }
        }

        return false; // No valid pattern found
    }
}
