class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        
        // Step 1: Calculate absolute differences and populate the frequency array
        int[] count = new int[100001];
        long totalDiff = 0;
        int maxDiff = 0;
        
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            if (diff > 0) {
                count[diff]++;
                maxDiff = Math.max(maxDiff, diff);
                totalDiff += diff;
            }
        }
        
        // If we have enough operations to reduce all differences to 0
        if (totalDiff <= k) {
            return 0;
        }
        
        // Step 2: Greedily reduce the largest differences
        for (int i = maxDiff; i > 0 && k > 0; i--) {
            if (count[i] > 0) {
                // We can at most reduce all items in this bucket, or whatever k allows
                long take = Math.min((long) count[i], k);
                
                // Shift the reduced items to the (i - 1) bucket
                count[i] -= take;
                count[i - 1] += take;
                
                k -= take;
            }
        }
        
        // Step 3: Calculate the final sum of squares
        long ans = 0;
        for (int i = maxDiff; i > 0; i--) {
            if (count[i] > 0) {
                ans += (long) count[i] * (long) i * i;
            }
        }
        
        return ans;
    }
}