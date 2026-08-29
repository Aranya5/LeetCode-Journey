import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        // Sort the values
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        // Map each value to a group.
        // A new group starts whenever the gap is > limit.
        Map<Integer, Integer> groupMap = new HashMap<>();
        int group = 0;

        groupMap.put(sorted[0], group);

        for (int i = 1; i < n; i++) {
            if ((long) sorted[i] - sorted[i - 1] > limit) {
                group++;
            }
            groupMap.put(sorted[i], group);
        }

        // For each group, store its values in a queue.
        Map<Integer, Queue<Integer>> groups = new HashMap<>();

        for (int value : sorted) {
            int g = groupMap.get(value);
            groups.computeIfAbsent(g, k -> new LinkedList<>()).offer(value);
        }

        // Put the smallest available value from the same group
        // at each original index.
        for (int i = 0; i < n; i++) {
            int g = groupMap.get(nums[i]);
            nums[i] = groups.get(g).poll();
        }

        return nums;
    }
}