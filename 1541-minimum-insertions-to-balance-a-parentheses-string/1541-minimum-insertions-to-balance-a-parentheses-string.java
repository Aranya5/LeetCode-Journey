class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int rightNeeded = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                // If we need an odd number of right brackets, a previous '(' is missing 
                // its second consecutive ')'. We must insert one now.
                if (rightNeeded % 2 != 0) {
                    insertions++;
                    rightNeeded--;
                }
                // The current '(' requires two new ')'
                rightNeeded += 2;
            } else {
                // We found a ')', decrement the demand
                rightNeeded--;
                
                // If rightNeeded goes negative, we have an orphaned ')'
                if (rightNeeded < 0) {
                    insertions++; // Insert a matching '('
                    rightNeeded += 2; // The inserted '(' demands two ')', minus the one we just processed
                }
            }
        }
        
        // Add any remaining unresolved demands
        return insertions + rightNeeded;
    }
}