import java.util.Arrays;
import java.util.TreeMap;

class Solution {
    // Helper structure to maintain a sliding window median and sums
    static class SlidingMedianWindow {
        private final TreeMap<Integer, Integer> left = new TreeMap<>();
        private final TreeMap<Integer, Integer> right = new TreeMap<>();
        private int leftSize = 0, rightSize = 0;
        private long leftSum = 0, rightSum = 0;
        private final int x;
        private final int targetLeftSize;

        public SlidingMedianWindow(int x) {
            this.x = x;
            this.targetLeftSize = (x + 1) / 2;
        }

        private void add(TreeMap<Integer, Integer> map, int val) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }

        private void remove(TreeMap<Integer, Integer> map, int val) {
            int count = map.get(val);
            if (count == 1) {
                map.remove(val);
            } else {
                map.put(val, count - 1);
            }
        }

        public void insert(int val) {
            if (leftSize == 0 || val <= left.lastKey()) {
                add(left, val);
                leftSize++;
                leftSum += val;
            } else {
                add(right, val);
                rightSize++;
                rightSum += val;
            }
            rebalance();
        }

        public void erase(int val) {
            if (left.containsKey(val)) {
                remove(left, val);
                leftSize--;
                leftSum -= val;
            } else {
                remove(right, val);
                rightSize--;
                rightSum -= val;
            }
            rebalance();
        }

        private void rebalance() {
            while (leftSize > targetLeftSize) {
                int maxLeft = left.lastKey();
                remove(left, maxLeft);
                leftSize--;
                leftSum -= maxLeft;

                add(right, maxLeft);
                rightSize++;
                rightSum += maxLeft;
            }
            while (leftSize < targetLeftSize && rightSize > 0) {
                int minRight = right.firstKey();
                remove(right, minRight);
                rightSize--;
                rightSum -= minRight;

                add(left, minRight);
                leftSize++;
                leftSum += minRight;
            }
        }

        public long getCost() {
            long median = left.lastKey();
            long costLeft = median * leftSize - leftSum;
            long costRight = rightSum - median * rightSize;
            return costLeft + costRight;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] windowCost = new long[n]; // windowCost[i] = cost for nums[i - x + 1 ... i]

        SlidingMedianWindow window = new SlidingMedianWindow(x);
        for (int i = 0; i < n; i++) {
            window.insert(nums[i]);
            if (i >= x) {
                window.erase(nums[i - x]);
            }
            if (i >= x - 1) {
                windowCost[i] = window.getCost();
            }
        }

        // dp[j][i]: min operations to form j subarrays within nums[0 ... i - 1]
        long INF = (long) 1e18;
        long[][] dp = new long[k + 1][n + 1];
        for (int j = 1; j <= k; j++) {
            Arrays.fill(dp[j], INF);
        }

        for (int j = 1; j <= k; j++) {
            for (int i = j * x; i <= n; i++) {
                // Option 1: Do not pick a subarray ending at index i - 1
                long res = dp[j][i - 1];

                // Option 2: Pick the subarray nums[i - x ... i - 1]
                long prev = dp[j - 1][i - x];
                if (prev != INF) {
                    res = Math.min(res, prev + windowCost[i - 1]);
                }
                dp[j][i] = res;
            }
        }

        return dp[k][n];
    }
}