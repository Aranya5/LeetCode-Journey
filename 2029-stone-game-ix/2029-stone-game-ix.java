class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] counts = new int[3];
        
        // Step 1: Count the frequencies of remainders
        for (int stone : stones) {
            counts[stone % 3]++;
        }
        
        // Step 2: Evaluate winner based on the parity of 0s
        if (counts[0] % 2 == 0) {
            // If even 0s, Alice needs at least one 1 and one 2
            return counts[1] > 0 && counts[2] > 0;
        } else {
            // If odd 0s, Alice needs a massive imbalance to survive the turn-skip
            return Math.abs(counts[1] - counts[2]) > 2;
        }
    }
}