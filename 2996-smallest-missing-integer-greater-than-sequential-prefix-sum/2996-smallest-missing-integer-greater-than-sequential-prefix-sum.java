import java.util.HashSet;
import java.util.Set;

class Solution {
    public int missingInteger(int[] nums) {
        // Step 1: Calculate the sum of the longest sequential prefix
        int prefixSum = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            // If it's sequential, add to sum. Otherwise, the prefix ends.
            if (nums[i] == nums[i - 1] + 1) {
                prefixSum += nums[i];
            } else {
                break;
            }
        }
        
        // Step 2: Store all numbers in a HashSet for instant O(1) lookups
        Set<Integer> uniqueNums = new HashSet<>();
        for (int num : nums) {
            uniqueNums.add(num);
        }
        
        // Step 3: Increment the sum until we find a number NOT in the set
        while (uniqueNums.contains(prefixSum)) {
            prefixSum++;
        }
        
        return prefixSum;
    }
}