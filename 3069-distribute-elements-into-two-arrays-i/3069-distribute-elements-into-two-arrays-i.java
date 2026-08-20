class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;

        int[] arr1 = new int[n];
        int[] arr2 = new int[n];

        int x = 0, y = 0;

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                arr1[x++] = nums[i];
            } else if (i == 1) {
                arr2[y++] = nums[i];
            } else {
                if (arr1[x - 1] > arr2[y - 1]) {
                    arr1[x++] = nums[i];
                } else {
                    arr2[y++] = nums[i];
                }
            }
        }

        for (int i = 0; i < y; i++) {
            arr1[x++] = arr2[i];
        }

        return arr1;

    }
}