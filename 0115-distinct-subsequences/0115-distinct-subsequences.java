class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        
        // dp[j] stores the number of distinct subsequences of s matching t[0...j-1]
        int[] dp = new int[n + 1];
        
        // Base case: There is exactly 1 way to form an empty string t (by deleting everything)
        dp[0] = 1; 
        
        for (int i = 1; i <= m; i++) {
            // Traverse backwards to prevent overwriting dp[j-1] before it is used
            for (int j = n; j >= 1; j--) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        
        return dp[n];
    }
}