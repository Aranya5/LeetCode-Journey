import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long totalSubarrays = 0;
        long cost = 0;
        int r = n - 1;

        // Each element in deque is [value, count]
        // Maintained in strictly increasing order of 'value' from front to back
        Deque<long[]> deque = new ArrayDeque<>();

        for (int l = n - 1; l >= 0; l--) {
            long val = nums[l];
            long count = 1;

            // Merge blocks from the front that are dominated by nums[l]
            while (!deque.isEmpty() && deque.peekFirst()[0] <= val) {
                long[] front = deque.pollFirst();
                cost += (val - front[0]) * front[1];
                count += front[1];
            }
            deque.offerFirst(new long[]{val, count});

            // Shrink the window from the right while operations exceed k
            while (cost > k) {
                long[] back = deque.peekLast();
                cost -= (back[0] - nums[r]);
                back[1]--;
                if (back[1] == 0) {
                    deque.pollLast();
                }
                r--;
            }

            // All subarrays starting at l and ending at l, l+1, ..., r are valid
            totalSubarrays += (r - l + 1);
        }

        return totalSubarrays;
    }
}