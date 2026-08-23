class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int sumLeft = 0, sumRight = 0;
        int qLeft = 0, qRight = 0;
        
        // Step 1: Process the left half
        for (int i = 0; i < n / 2; i++) {
            if (num.charAt(i) == '?') {
                qLeft++;
            } else {
                sumLeft += num.charAt(i) - '0';
            }
        }
        
        // Step 2: Process the right half
        for (int i = n / 2; i < n; i++) {
            if (num.charAt(i) == '?') {
                qRight++;
            } else {
                sumRight += num.charAt(i) - '0';
            }
        }
        
        // Step 3: Evaluate winner based on parity
        if ((qLeft + qRight) % 2 != 0) {
            return true; // Alice gets the last move and wins
        }
        
        // Step 4: Evaluate winner based on the perfect balance equation
        // If the equation holds, Bob wins (returns false). Otherwise, Alice wins.
        return (sumLeft - sumRight) != (qRight - qLeft) / 2 * 9;
    }
}