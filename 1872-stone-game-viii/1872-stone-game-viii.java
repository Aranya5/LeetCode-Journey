class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        
        // Step 1: Calculate the prefix sums in place
        for (int i = 1; i < n; i++) {
            stones[i] += stones[i - 1];
        }
        
        // Step 2: Initialize DP with the base case (picking the last element)
        int dp = stones[n - 1];
        
        // Step 3: Work backwards from the second-to-last element down to index 1
        for (int i = n - 2; i >= 1; i--) {
            dp = Math.max(dp, stones[i] - dp);
        }
        
        return dp;
    }
}