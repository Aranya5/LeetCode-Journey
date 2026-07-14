class Solution {
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1; // Anchor to the last valid index
        
        while (low <= high) {
            int mid = low + (high - low) / 2; // Anchor midpoint to low bound
            
            // 1. Check if we found the target value
            if (nums[mid] == target) {
                return mid;
            }
            
            // 2. Identify which half of the array is normally sorted
            if (nums[low] <= nums[mid]) {
                // Left half is sorted
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid - 1; // Search left
                } else {
                    low = mid + 1;  // Search right
                }
            } else {
                // Right half is sorted
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;  // Search right
                } else {
                    high = mid - 1; // Search left
                }
            }
        }
        
        return -1;
    }
}