class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int minSubArrayLen = Integer.MAX_VALUE;
        int low=0,high=0;
        int currentSum=0;

        for(high=0;high<nums.length;high++){
            currentSum += nums[high];
            while(currentSum >= target){
                int currArrayLen =high-low+1;
                minSubArrayLen = Math.min(minSubArrayLen,currArrayLen);
                currentSum -= nums[low];
                low++;
            }
        }

        return minSubArrayLen == Integer.MAX_VALUE?0:minSubArrayLen;

        
    }
}