import java.util.Arrays;

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxVal = 0;
        for (int x : nums) {
            maxVal = Math.max(maxVal, x);
        }

        // Step 1: Count frequency of each number in nums
        int[] count = new int[maxVal + 1];
        for (int x : nums) {
            count[x]++;
        }

        // Step 2: Count how many pairs have a GCD that is a multiple of i
        long[] gcdMultiplesCount = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            long totalMultiples = 0;
            for (int j = i; j <= maxVal; j += i) {
                totalMultiples += count[j];
            }
            gcdMultiplesCount[i] = totalMultiples * (totalMultiples - 1) / 2;
        }

        // Step 3: Use inclusion-exclusion from largest to smallest to get exact GCD counts
        long[] exactGcdCount = new long[maxVal + 1];
        for (int i = maxVal; i >= 1; i--) {
            exactGcdCount[i] = gcdMultiplesCount[i];
            for (int j = 2 * i; j <= maxVal; j += i) {
                exactGcdCount[i] -= exactGcdCount[j];
            }
        }

        // Step 4: Build a prefix sum array to represent cumulative pair counts
        long[] prefixSum = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            prefixSum[i] = prefixSum[i - 1] + exactGcdCount[i];
        }

        // Step 5: Answer each query using binary search
        int qLen = queries.length;
        int[] ans = new int[qLen];
        for (int i = 0; i < qLen; i++) {
            long targetRank = queries[i] + 1; // 1-based rank
            int low = 1, high = maxVal, foundGcd = maxVal;
            
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (prefixSum[mid] >= targetRank) {
                    foundGcd = mid;
                    high = mid - 1; // Try to find a smaller valid GCD value
                } else {
                    low = mid + 1;
                }
            }
            ans[i] = foundGcd;
        }

        return ans;
    }
}
