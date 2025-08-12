/*
    PoTD Date: 12th Aug 2025

    Quick Flashcard:
    -----------------
    Problem:
    - We have an array 'prices[]' representing candy prices.
    - For every candy bought, we can get up to 'k' other candies for free.
    - Need to find:
        1. Minimum total money required to buy all candies.
        2. Maximum total money required to buy all candies.
    - Must always take maximum possible free candies each time.

    Approach:
    - Sort the prices array.
    - For minimum cost:
        Buy cheapest candies first and get most expensive ones for free.
    - For maximum cost:
        Buy most expensive candies first and get cheapest ones for free.
    - Greedy strategy ensures optimal results.

    Constraints:
    - 1 ≤ prices.size() ≤ 10^5
    - 0 ≤ k ≤ prices.size()
    - 1 ≤ prices[i] ≤ 10^4
*/

class Solution {
    public ArrayList<Integer> minMaxCandy(int[] prices, int k) {
        // Step 1: Sort prices in ascending order for easy min/max calculations
        Arrays.sort(prices);
        int n = prices.length;

        // Calculate Minimum Cost
        int minCost = 0;
        int i = 0, remaining = n;
        // Loop until all candies are bought or taken for free
        while (i < remaining) {
            minCost += prices[i];  // Buy cheapest candy
            i++;
            remaining -= k;        // Take k candies for free (most expensive available)
        }

        // Calculate Maximum Cost
        int maxCost = 0;
        int j = n - 1, index = -1;
        // Loop until all candies are bought or taken for free
        while (j > index) {
            maxCost += prices[j];  // Buy most expensive candy
            j--;
            index += k;            // Take k candies for free (cheapest available)
        }

        // Store results in list
        ArrayList<Integer> result = new ArrayList<>();
        result.add(minCost);
        result.add(maxCost);
        return result;
    }
}

/*
    Time Complexity:
    - Sorting: O(n log n)
    - Min/Max calculation: O(n)
    - Overall: O(n log n)

    Space Complexity:
    - O(1) extra space (apart from input array and result list)
*/
