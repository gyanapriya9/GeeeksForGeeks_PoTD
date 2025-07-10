// Class representing a node in the Trie
class TrieNode {
    TrieNode[] children = new TrieNode[26]; // 26 lowercase English letters
    boolean isEnd = false; // Indicates if a word ends at this node

    TrieNode() {
        Arrays.fill(children, null); // Initialize all children as null
    }
}

// Trie class with insert and prefix-checking functionality
class Trie {
    TrieNode root;

    Trie() {
        root = new TrieNode(); // Initialize root node
    }

    // Function to insert a word into the Trie
    void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a'; // Map character to index 0-25
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode(); // Create a new node if not present
            }
            node = node.children[idx]; // Move to the child node
        }
        node.isEnd = true; // Mark the end of the word
    }

    // Function to check if all prefixes of a word exist in the Trie
    boolean allPrefixesExist(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            node = node.children[idx];

            // If a prefix is missing or not marked as end of a valid word
            if (node == null || !node.isEnd) {
                return false;
            }
        }
        return true; // All prefixes exist
    }
}

class Solution {
    public String longestString(String[] words) {
        Trie trie = new Trie();

        // Step 1: Insert all words into the Trie
        for (String word : words) {
            trie.insert(word);
        }

        String result = "";

        // Step 2: Sort the words lexicographically to ensure smallest is picked first
        Arrays.sort(words);

        // Step 3: Check each word if all its prefixes exist
        for (String word : words) {
            if (trie.allPrefixesExist(word)) {
                // Step 4: Update result if current word is longer,
                // or same length but lexicographically smaller
                if (word.length() > result.length() ||
                    (word.length() == result.length() && word.compareTo(result) < 0)) {
                    result = word;
                }
            }
        }

        return result; // Final longest valid word
    }
}
