// 2025-06-13 PoTD

// 🧠 Problem Summary in Layman Terms:
// We're given a string with both capital (uppercase) and small (lowercase) letters.
// We need to sort all capital letters among themselves and all small letters among themselves,
// BUT at the same position in the string as they were.
// So if 1st letter was a capital, the new sorted capital letter should also go there.

class Solution {

    // 💡 Function to sort uppercase and lowercase characters separately
    public static String caseSort(String s) {

        int n = s.length();

        // Arrays to count frequency of each lowercase and uppercase letter (a-z, A-Z)
        int[] lowercaseFreq = new int[26];
        int[] uppercaseFreq = new int[26];

        // Step 1: Count how many times each lowercase and uppercase character appears
        for (char ch : s.toCharArray()) {
            if (Character.isLowerCase(ch)) {
                // Count for lowercase character (like 'a' -> index 0, 'b' -> index 1)
                lowercaseFreq[ch - 'a']++;
            } else {
                // Count for uppercase character (like 'A' -> index 0, 'B' -> index 1)
                uppercaseFreq[ch - 'A']++;
            }
        }

        // StringBuilder to store final result with sorted characters placed correctly
        StringBuilder sortedString = new StringBuilder(s);

        // Pointers to track which lowercase or uppercase letter to use next
        int l = 0; // Pointer for lowercase
        int u = 0; // Pointer for uppercase

        // Step 2: Replace original characters with sorted ones of the same case
        for (int i = 0; i < n; i++) {

            // If original character is lowercase, replace it with next smallest lowercase
            if (Character.isLowerCase(s.charAt(i))) {

                // Move pointer to next available lowercase character
                while (lowercaseFreq[l] == 0) {
                    l++;
                }

                // Place that character in the result
                sortedString.setCharAt(i, (char) ('a' + l));

                // Reduce frequency since we've used one
                lowercaseFreq[l]--;

            } else {
                // Same logic for uppercase characters

                while (uppercaseFreq[u] == 0) {
                    u++;
                }

                sortedString.setCharAt(i, (char) ('A' + u));
                uppercaseFreq[u]--;
            }
        }

        // Step 3: Return the modified sorted string
        return sortedString.toString();
    }
}
