class Solution {
    public int maxTurbulenceSize(int[] arr) {
        
        if (arr == null || arr.length == 0) {
            return 0;
        }
        
        
        int inc = 1; 
        int dec = 1; 
        int maxLen = 1;
        
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
               
                inc = dec + 1;
              
                dec = 1;
                
            } else if (arr[i] < arr[i - 1]) {
                dec = inc + 1;
                inc = 1;
                
            } else {
                inc = 1;
                dec = 1;
            }
            
            maxLen = Math.max(maxLen, Math.max(inc, dec));
        }
        
        return maxLen;
    }
}