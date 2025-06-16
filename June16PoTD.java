class Solution {
    // Helper function to calculate the cost of equalizing to a given height
    private int costOfOperation(int[] heights, int[] cost, int targetHeight) {
        int totalCost = 0;
        int n = heights.length;

        // Calculate cost for each tower to match targetHeight
        for (int i = 0; i < n; i++) {
            totalCost += Math.abs(heights[i] - targetHeight) * cost[i];
        }

        return totalCost;
    }

    // Function to find the minimum cost to equalize all towers
    public int minCost(int[] heights, int[] cost) {
        int n = heights.length;
        int max_h = Arrays.stream(heights).max().getAsInt();
        int minCost = Integer.MAX_VALUE;

        // Binary search for the optimal height
        int low = 0, high = max_h + 1;
        while (low < high) {
            int mid = (low + high) / 2;

            int costAtMidMinus1 =
                (mid > 0) ? costOfOperation(heights, cost, mid - 1) : Integer.MAX_VALUE;
            int costAtMid = costOfOperation(heights, cost, mid);
            int costAtMidPlus1 = costOfOperation(heights, cost, mid + 1);

            minCost = Math.min(minCost, costAtMid);

            if (costAtMidMinus1 <= costAtMid) {
                high = mid;
            } else if (costAtMidPlus1 <= costAtMid) {
                low = mid + 1;
            } else {
                return costAtMid;
            }
        }

        return minCost;
    }
}