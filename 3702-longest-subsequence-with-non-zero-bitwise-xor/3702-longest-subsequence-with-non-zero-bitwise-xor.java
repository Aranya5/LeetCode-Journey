class Solution {
    public int longestSubsequence(int[] nums) {
        int totalXor = 0;
        boolean hasNonZero = false;
        
        // Compute the total XOR and check for any non-zero elements
        for (int num : nums) {
            totalXor ^= num;
            if (num != 0) {
                hasNonZero = true;
            }
        }
        
        // Scenario 1: The whole array is valid
        if (totalXor != 0) {
            return nums.length;
        }
        
        // Scenario 2: Total XOR is 0, but we can drop one non-zero element
        if (hasNonZero) {
            return nums.length - 1;
        }
        
        // Scenario 3: All elements are zero
        return 0;
    }
}