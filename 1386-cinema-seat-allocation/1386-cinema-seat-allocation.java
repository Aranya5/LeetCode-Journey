import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowToReserved = new HashMap<>();
        
        // Step 1: Build the bitmask for each row containing reserved seats
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            
            // We only care about seats 2 through 9
            if (col >= 2 && col <= 9) {
                rowToReserved.put(row, rowToReserved.getOrDefault(row, 0) | (1 << col));
            }
        }
        
        // Step 2: Calculate max families for completely empty rows
        int maxFamilies = (n - rowToReserved.size()) * 2;
        
        // Step 3: Calculate max families for rows with reservations
        for (int mask : rowToReserved.values()) {
            boolean leftFree = (mask & 60) == 0;
            boolean rightFree = (mask & 960) == 0;
            boolean midFree = (mask & 240) == 0;
            
            if (leftFree && rightFree) {
                maxFamilies += 2;
            } else if (leftFree || rightFree || midFree) {
                maxFamilies += 1;
            }
        }
        
        return maxFamilies;
    }
}