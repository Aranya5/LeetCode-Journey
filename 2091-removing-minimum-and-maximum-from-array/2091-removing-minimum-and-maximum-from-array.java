class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;

        int minIndex = 0;
        int maxIndex = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        int rightIndex = Math.max(minIndex, maxIndex);
        int leftIndex = Math.min(minIndex, maxIndex);

        return Math.min(leftIndex + 1 + n - rightIndex, Math.min(rightIndex + 1, n - leftIndex));

    }
}