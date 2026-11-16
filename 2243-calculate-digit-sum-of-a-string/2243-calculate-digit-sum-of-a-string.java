class Solution {
    public String digitSum(String s, int k) {
        // Continue performing rounds while the string length exceeds k
        while (s.length() > k) {
            StringBuilder nextRound = new StringBuilder();
            
            // Step through the string in chunks of size k
            for (int i = 0; i < s.length(); i += k) {
                int sum = 0;
                // Ensure we don't go out of bounds on the final group
                int end = Math.min(i + k, s.length());
                
                for (int j = i; j < end; j++) {
                    sum += s.charAt(j) - '0';
                }
                
                nextRound.append(sum);
            }
            
            s = nextRound.toString();
        }
        
        return s;
    }
}