class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int[] prefix = new int[n + 1];
        
        // Step 1: Build the prefix sum array
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums.get(i);
        }
        
        int minSum = Integer.MAX_VALUE;
        
        // Step 2: Check every subarray of length between l and r
        for (int len = l; len <= r; len++) {
            for (int i = 0; i + len <= n; i++) {
                int currentSum = prefix[i + len] - prefix[i];
                
                // Update minimum only if the sum is strictly positive
                if (currentSum > 0) {
                    minSum = Math.min(minSum, currentSum);
                }
            }
        }
        
        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}