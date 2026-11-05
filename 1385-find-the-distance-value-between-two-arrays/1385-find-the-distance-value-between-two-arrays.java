class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int distanceValue = 0;

        for (int x : arr1) {
            boolean isValid = true;
            for (int y : arr2) {
                if (Math.abs(x - y) <= d) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                distanceValue++;
            }
        }

        return distanceValue;
    }
}