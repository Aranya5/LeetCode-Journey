class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        int numSubsets = 1 << n;
        
        long[] lcms = new long[numSubsets];
        int[] signs = new int[numSubsets];
        
        // Precompute the LCM and Inclusion-Exclusion sign for all 2^n - 1 subsets
        lcms[0] = 1;
        for (int i = 1; i < numSubsets; i++) {
            int lowestBit = Integer.numberOfTrailingZeros(i);
            int prevSubset = i ^ (1 << lowestBit);
            
            if (prevSubset == 0) {
                // Subset of size 1
                lcms[i] = coins[lowestBit];
                signs[i] = 1; // Odd size: Add
            } else {
                // Subset of size > 1
                long prevLcm = lcms[prevSubset];
                long currentCoin = coins[lowestBit];
                
                lcms[i] = (prevLcm / gcd(prevLcm, currentCoin)) * currentCoin;
                signs[i] = signs[prevSubset] * -1; // Flip the sign
            }
        }
        
        // Binary Search Space
        long left = 1;
        long right = (long) k * coins[0];
        for (int c : coins) {
            right = Math.min(right, (long) k * c);
        }
        
        long ans = right;
        
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long count = 0;
            
            // Apply Inclusion-Exclusion for this 'mid'
            for (int i = 1; i < numSubsets; i++) {
                count += signs[i] * (mid / lcms[i]);
            }
            
            if (count >= k) {
                ans = mid;
                right = mid - 1; // Try to find a tighter, smaller bound
            } else {
                left = mid + 1; // We need more multiples
            }
        }
        
        return ans;
    }
    
    // Helper method to compute Greatest Common Divisor
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}