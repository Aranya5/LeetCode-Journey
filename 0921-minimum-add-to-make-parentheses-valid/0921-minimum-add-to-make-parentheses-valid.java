class Solution {
    public int minAddToMakeValid(String s) {
        int openCount = 0;
        int movesNeeded = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                openCount++;
            } else {
                if (openCount > 0) {
                    openCount--; // Pair found, resolve the dependency
                } else {
                    movesNeeded++; // Orphaned ')', requires an inserted '('
                }
            }
        }
        
        // Add any remaining unresolved '(' that need a matching ')'
        return movesNeeded + openCount;
    }
}