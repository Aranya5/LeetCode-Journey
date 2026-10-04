class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0;
        int maxOpen = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                minOpen = Math.max(0, minOpen - 1);
                maxOpen--;
            } else { // c == '*'
                minOpen = Math.max(0, minOpen - 1);
                maxOpen++;
            }
            
            // If the maximum possible open parentheses becomes negative, 
            // it means there are too many closing parentheses.
            if (maxOpen < 0) {
                return false;
            }
        }
        
        // If minOpen is 0, all open parentheses could be successfully closed.
        return minOpen == 0;
    }
}