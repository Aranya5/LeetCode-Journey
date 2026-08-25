class Solution {
    public int missingMultiple(int[] nums, int k) {
        Set<Integer> divSet = new HashSet<>();
        for (int n : nums) {
            if (n % k == 0) {
                divSet.add(n);
            }
        }

        int ans = k;
        while (divSet.contains(ans)) {
            ans += k;
        }
        return ans;

    }
}