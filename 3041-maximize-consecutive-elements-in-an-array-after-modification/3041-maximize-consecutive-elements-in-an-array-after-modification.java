class Solution {
    public int maxSelectedElements(int[] nums) {
        // Step 1: Sort the array to process elements in ascending order
        Arrays.sort(nums);
        
        // Max possible value is 10^6, so max modified value is 10^6 + 1
        int maxVal = nums[nums.length - 1];
        int[] dp = new int[maxVal + 2];
        
        int maxConsecutive = 0;
        
        // Step 2: Evaluate both choices (x + 1 and x) for every element
        for (int x : nums) {
            // Must update x + 1 first so it uses the previous state of dp[x]
            dp[x + 1] = dp[x] + 1;
            dp[x] = dp[x - 1] + 1;
            
            maxConsecutive = Math.max(maxConsecutive, Math.max(dp[x], dp[x + 1]));
        }
        
        return maxConsecutive;
    }
}