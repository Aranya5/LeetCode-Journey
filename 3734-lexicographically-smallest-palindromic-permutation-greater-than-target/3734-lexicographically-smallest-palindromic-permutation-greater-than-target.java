class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] counts = new int[26];
        
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }
        
        int oddCount = 0;
        char midChar = 0;
        int[] pool = new int[26];
        
        // Validate palindrome possibility and build the first-half pool
        for (int i = 0; i < 26; i++) {
            if (counts[i] % 2 != 0) {
                oddCount++;
                midChar = (char) (i + 'a');
            }
            pool[i] = counts[i] / 2;
        }
        
        if (oddCount > 1) return "";
        
        int halfLen = n / 2;
        
        // Try matching a prefix of length L, from max possible down to 0
        for (int L = halfLen; L >= 0; L--) {
            int[] tempPool = pool.clone();
            boolean canFormPrefix = true;
            
            // Check if we have the characters to match target up to index L - 1
            for (int i = 0; i < L; i++) {
                int charIdx = target.charAt(i) - 'a';
                if (tempPool[charIdx] == 0) {
                    canFormPrefix = false;
                    break;
                }
                tempPool[charIdx]--;
            }
            
            if (!canFormPrefix) continue;
            
            if (L == halfLen) {
                // Test exact first-half match
                StringBuilder sb = new StringBuilder(target.substring(0, L));
                if (n % 2 != 0) sb.append(midChar);
                for (int i = L - 1; i >= 0; i--) {
                    sb.append(target.charAt(i));
                }
                if (sb.toString().compareTo(target) > 0) {
                    return sb.toString();
                }
            } else {
                // Find the smallest character strictly greater than target[L]
                char needed = target.charAt(L);
                char bestNext = 0;
                for (char c = (char) (needed + 1); c <= 'z'; c++) {
                    if (tempPool[c - 'a'] > 0) {
                        bestNext = c;
                        break;
                    }
                }
                
                if (bestNext != 0) {
                    tempPool[bestNext - 'a']--;
                    StringBuilder half = new StringBuilder(target.substring(0, L));
                    half.append(bestNext);
                    
                    // Fill the rest with the smallest available characters
                    for (char c = 'a'; c <= 'z'; c++) {
                        while (tempPool[c - 'a'] > 0) {
                            half.append(c);
                            tempPool[c - 'a']--;
                        }
                    }
                    
                    // Construct the final palindrome
                    StringBuilder fullPal = new StringBuilder(half.toString());
                    if (n % 2 != 0) fullPal.append(midChar);
                    for (int i = half.length() - 1; i >= 0; i--) {
                        fullPal.append(half.charAt(i));
                    }
                    return fullPal.toString();
                }
            }
        }
        
        return "";
    }
}