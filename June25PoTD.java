// 📅 PoTD 24 June 2025
// 🧠 Problem: Check if Frequencies Can Be Made Equal
// 💡 Layman’s Take: You’re given a word, and your goal is to check if by removing **just one character**, the rest of the letters can have **equal frequency**. For example, if you had "xyyz", by removing one 'y', all letters would appear once. If that’s possible, return `true`; otherwise, return `false`.

class Solution {
    public boolean sameFreq(String s) {
        // Array to hold count of each letter from 'a' to 'z'
        int[] charCounts = new int[26];

        // Count how many times each letter appears
        for (char ch : s.toCharArray()) {
            charCounts[ch - 'a']++;
        }

        // Map to track how many letters share the same frequency
        Map<Integer, Integer> freqMap = new HashMap<>();

        // Build the frequency map
        for (int count : charCounts) {
            if (count > 0) {
                freqMap.put(count, freqMap.getOrDefault(count, 0) + 1);
            }
        }

        // Case 1: All letters already have the same frequency
        if (freqMap.size() == 1) {
            return true;
        }

        // Case 2: Two different frequencies exist — check if one can be fixed by removing a single letter
        if (freqMap.size() == 2) {
            Iterator<Map.Entry<Integer, Integer>> it = freqMap.entrySet().iterator();
            Map.Entry<Integer, Integer> entry1 = it.next();
            Map.Entry<Integer, Integer> entry2 = it.next();

            int freq1 = entry1.getKey(), count1 = entry1.getValue();
            int freq2 = entry2.getKey(), count2 = entry2.getValue();

            // Scenario A: One of the frequencies is 1 and only one character has that frequency
            if ((freq1 == 1 && count1 == 1) || (freq2 == 1 && count2 == 1)) {
                return true;
            }

            // Scenario B: The two frequencies differ by 1 and the higher frequency appears only once
            if (Math.abs(freq1 - freq2) == 1 &&
                ((count1 == 1 && freq1 > freq2) || (count2 == 1 && freq2 > freq1))) {
                return true;
            }
        }

        // In all other cases, it’s not possible to equalize by removing just one character
        return false;
    }
}
