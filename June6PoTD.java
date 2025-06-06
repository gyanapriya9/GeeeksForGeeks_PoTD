// June 6, 2025 – GFG PoTD 2.0

// 🧩 Question: Search Pattern using Rabin-Karp Algorithm
/*
Given:
- A `text` string and a `pattern` string.
Return all positions (1-based) in `text` where `pattern` occurs as a substring.

Rabin-Karp is a rolling hash-based substring matching algorithm.
It uses hashing to quickly check if substrings match without comparing every character.
*/

// ✅ Rabin-Karp Algorithm Explanation:
/*
1. Precompute the hash of the pattern.
2. Slide a window of the same length over the text and compute the hash for each window.
3. If the hash matches:
   - Verify by checking characters one-by-one (to avoid false positives due to collisions).
4. Return 1-based index positions of all valid matches.
*/

class Solution {

    public static ArrayList<Integer> search(String pat, String txt) {

        // Alphabet size (all lowercase English letters)
        int d = 256;

        // A prime number to reduce hash collisions
        int q = 101;

        int m = pat.length(); // length of pattern
        int n = txt.length(); // length of text

        int pHash = 0; // pattern hash
        int tHash = 0; // text hash
        int h = 1;

        ArrayList<Integer> result = new ArrayList<>();

        // Calculate h = pow(d, m-1) % q
        for (int i = 0; i < m - 1; i++) {
            h = (h * d) % q;
        }

        // Calculate initial hashes of pattern and first window
        for (int i = 0; i < m; i++) {
            pHash = (d * pHash + pat.charAt(i)) % q;
            tHash = (d * tHash + txt.charAt(i)) % q;
        }

        // Slide over text
        for (int i = 0; i <= n - m; i++) {
            if (pHash == tHash) {
                boolean match = true;

                // Confirm with character comparison
                for (int j = 0; j < m; j++) {
                    if (txt.charAt(i + j) != pat.charAt(j)) {
                        match = false;
                        break;
                    }
                }

                if (match) result.add(i + 1); // 1-based index
            }

            // Rolling hash: calculate hash for next window
            if (i < n - m) {
                tHash = (d * (tHash - txt.charAt(i) * h) + txt.charAt(i + m)) % q;

                // Handle negative hash
                if (tHash < 0) tHash += q;
            }
        }

        return result;
    }
}
