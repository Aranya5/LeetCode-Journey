class Solution {
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                swap(i, idx, nums);
                idx++;
            }

        }

    }

    public void swap(int a, int b, int[] arr) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}