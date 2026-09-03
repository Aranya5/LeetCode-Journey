class Solution {
    public boolean uniformArray(int[] nums1) {
        int min = Integer.MAX_VALUE;

        for (int num : nums1) {
            if (num < min) {
                min = num;
            }
        }

        //if min is odd then all can be converted into odd or even
        if (min % 2 != 0) {
            return true;
        }

        //if even the all should be even to be true
        for (int num : nums1) {
            if (num % 2 != 0) {
                return false;
            }
        }

        return true;
    }

}
