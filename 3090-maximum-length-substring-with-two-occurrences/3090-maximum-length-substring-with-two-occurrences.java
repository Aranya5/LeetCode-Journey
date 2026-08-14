class Solution {
    public int maximumLengthSubstring(String s) {
        int left = 0;
        int maxLength = 0;
        // Array to store frequencies of standard ASCII characters
        int[] freq = new int[128]; 

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            freq[currentChar]++;
            
            // If the constraint is broken, shrink the window from the left
            while (freq[currentChar] > 2) {
                char leftChar = s.charAt(left);
                freq[leftChar]--;
                left++;
            }
            
            // Update the maximum length found so far
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}