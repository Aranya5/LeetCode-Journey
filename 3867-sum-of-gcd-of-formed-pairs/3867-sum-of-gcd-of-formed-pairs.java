import java.util.Arrays;

class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        int[] prefixGcd = new int[n];
        int currentMax = 0;
        
        // Phase 1: Build the prefixGcd array
        for (int i = 0; i < n; i++) {
            currentMax = Math.max(currentMax, nums[i]);
            prefixGcd[i] = findGcd(nums[i], currentMax);
        }
        
        // Phase 2: Sort the array
        Arrays.sort(prefixGcd);
        
        // Phase 3: Two-pointer pairing
        long totalSum = 0;
        int left = 0;
        int right = n - 1;
        
        // Stops strictly before left equals right, inherently dropping the middle odd element
        while (left < right) {
            totalSum += findGcd(prefixGcd[left], prefixGcd[right]);
            left++;
            right--;
        }
        
        return totalSum;
    }
    
    // Helper method to compute GCD 
    private int findGcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}