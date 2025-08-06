// Today Date PoTD : 6th Aug 2025
// Problem Title: Roman Number to Integer

/*
Flashcard Style Problem Explanation:
------------------------------------
Task: Convert a Roman numeral string to an integer.
Symbols and Values:
  I = 1, V = 5, X = 10, L = 50, 
  C = 100, D = 500, M = 1000

Roman numeral rules:
- If a smaller value comes before a larger value, subtract it. (e.g., IV = 5 - 1 = 4)
- Otherwise, add it. (e.g., VI = 5 + 1 = 6)

Approach:
- Traverse the string.
- For each character, compare it with the next one:
  → If current ≥ next → add value
  → Else → subtract current from next and move two steps
*/

class Solution {

    // Helper function to get integer value for a Roman character
    int value(char r) {
        if (r == 'I') return 1;
        if (r == 'V') return 5;
        if (r == 'X') return 10;
        if (r == 'L') return 50;
        if (r == 'C') return 100;
        if (r == 'D') return 500;
        if (r == 'M') return 1000;
        return -1; // Invalid character
    }

    // Main function to convert Roman numeral string to Integer
    public int romanToDecimal(String s) {
        int res = 0; // Initialize result

        // Loop through each character of the Roman string
        for (int i = 0; i < s.length(); i++) {

            // Get the integer value of current Roman character
            int s1 = value(s.charAt(i));

            // Check if there is a next character to compare
            if (i + 1 < s.length()) {
                int s2 = value(s.charAt(i + 1)); // Get value of next character

                if (s1 >= s2) {
                    // If current symbol value is greater or equal, just add it
                    res += s1;
                } else {
                    // If current is less than next, subtract current from next
                    // and skip the next character
                    res += (s2 - s1);
                    i++; // Skip next character as it's already used
                }
            } else {
                // Last character case, just add its value
                res += s1;
            }
        }

        return res; // Final integer value
    }
}

/*
Time Complexity: O(n), where n = length of the Roman string
Space Complexity: O(1), constant extra space used
*/
