import java.util.Arrays;

class Solution {
    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n + 1];
        // Use a safe large value to prevent overflow when adding jump costs
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        
        dp[0] = 0; // Starting at step 0 costs 0

        for (int i = 1; i <= n; i++) {
            int stepCost = costs[i - 1];
            for (int j = Math.max(0, i - 3); j < i; j++) {
                int jumpDist = i - j;
                int jumpCost = stepCost + jumpDist * jumpDist;
                dp[i] = Math.min(dp[i], dp[j] + jumpCost);
            }
        }

        return dp[n];
    }
}