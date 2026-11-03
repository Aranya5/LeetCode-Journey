class Solution {
    public int minMoves(int[] nums) {
        int maxVal = Integer.MIN_VALUE;
        int cumSum = 0;
        for (int num : nums) {
            cumSum += num;
            if (num > maxVal) {
                maxVal = num;
            }
        }

        return ((nums.length * maxVal) - cumSum);
    }
}