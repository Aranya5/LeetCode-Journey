class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;

        int INF = Integer.MAX_VALUE / 2;
        int[] best = new int[n];

        // best[i] = minimum length of a target-sum subarray
        // completely within arr[0...i]
        for (int i = 0; i < n; i++) {
            best[i] = INF;
        }

        int left = 0;
        int sum = 0;
        int ans = INF;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            // Since all numbers are positive, shrink from left
            // while sum is greater than target.
            while (sum > target) {
                sum -= arr[left++];
            }

            if (sum == target) {
                int len = right - left + 1;

                // Find the best non-overlapping subarray before 'left'
                if (left > 0 && best[left - 1] != INF) {
                    ans = Math.min(ans, len + best[left - 1]);
                }

                // best[right] should include this newly found subarray
                best[right] = Math.min(best[right], len);
            }

            // Carry forward the best subarray found so far
            if (right > 0) {
                best[right] = Math.min(best[right], best[right - 1]);
            }
        }

        return ans == INF ? -1 : ans;
    }
}