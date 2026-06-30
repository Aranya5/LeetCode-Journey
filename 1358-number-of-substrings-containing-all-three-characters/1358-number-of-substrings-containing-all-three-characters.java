class Solution {
    public int numberOfSubstrings(String s) {
        // Array to store the last seen index of 'a', 'b', and 'c'
        int[] lastSeen = {-1, -1, -1};
        int count = 0;
        
        for (int right = 0; right < s.length(); right++) {
            // Update the last seen index for the current character
            lastSeen[s.charAt(right) - 'a'] = right;
            
            // The window is valid only if we have seen all three characters at least once
            if (lastSeen[0] != -1 && lastSeen[1] != -1 && lastSeen[2] != -1) {
                // Find the bottleneck (the oldest character in our valid window)
                int minLastSeen = Math.min(lastSeen[0], Math.min(lastSeen[1], lastSeen[2]));
                
                // All substrings starting from index 0 up to minLastSeen are valid
                count += minLastSeen + 1;
            }
        }
        
        return count;
    }
}