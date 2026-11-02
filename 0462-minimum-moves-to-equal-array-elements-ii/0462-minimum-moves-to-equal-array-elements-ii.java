class Solution {
    public int minMoves2(int[] nums) {
        // Step 1: Sort the array to align elements for pairing
        Arrays.sort(nums);
        
        int left = 0;
        int right = nums.length - 1;
        int totalMoves = 0;
        
        // Step 2: Sum the absolute differences of symmetric pairs
        while (left < right) {
            totalMoves += nums[right] - nums[left];
            left++;
            right--;
        }
        
        return totalMoves;
    }
}