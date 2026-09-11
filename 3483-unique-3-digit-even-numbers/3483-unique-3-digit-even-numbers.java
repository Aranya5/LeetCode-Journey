class Solution {
    public int totalNumbers(int[] digits) {
        // Step 1: Build the inventory of available digits
        int[] available = new int[10];
        for (int digit : digits) {
            available[digit]++;
        }
        
        int validCount = 0;
        
        // Step 2: Test every possible 3-digit even number
        for (int num = 100; num <= 998; num += 2) {
            int[] required = new int[10];
            int temp = num;
            
            // Extract the three digits
            required[temp % 10]++;
            temp /= 10;
            required[temp % 10]++;
            temp /= 10;
            required[temp % 10]++;
            
            // Step 3: Verify if we have enough inventory to build this number
            boolean canBuild = true;
            for (int i = 0; i < 10; i++) {
                if (required[i] > available[i]) {
                    canBuild = false;
                    break;
                }
            }
            
            if (canBuild) {
                validCount++;
            }
        }
        
        return validCount;
    }
}