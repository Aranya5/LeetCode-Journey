import java.util.ArrayList;
import java.util.List;

class Solution {
    public String convert(String s, int numRows) {
        // Edge cases: 
        // 1. If there's only 1 row, the zigzag is just the string itself.
        // 2. If numRows is greater than the string length, no zigzagging actually occurs.
        if (numRows == 1 || numRows >= s.length()) {
            return s;
        }
        
        // Create a list of StringBuilders, one for each row
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }
        
        int currentRow = 0;
        boolean goingDown = false;
        
        // Loop through the string and place each character in the appropriate row
        for (char c : s.toCharArray()) {
            rows.get(currentRow).append(c);
            
            // If we hit the top or bottom row, we must reverse direction
            if (currentRow == 0 || currentRow == numRows - 1) {
                goingDown = !goingDown;
            }
            
            // Move up or down depending on our current direction
            currentRow += goingDown ? 1 : -1;
        }
        
        // Combine all the rows together to form the final string
        StringBuilder finalString = new StringBuilder();
        for (StringBuilder row : rows) {
            finalString.append(row);
        }
        
        return finalString.toString();
    }
}