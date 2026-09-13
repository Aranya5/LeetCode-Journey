import java.util.*;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        List<int[]> list1 = new ArrayList<>();
        List<int[]> list2 = new ArrayList<>();
        int n = img1.length;
        
        // Step 1: Filter out all the 0s and store coordinates of 1s
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img1[r][c] == 1) list1.add(new int[]{r, c});
                if (img2[r][c] == 1) list2.add(new int[]{r, c});
            }
        }
        
        Map<String, Integer> vectorCounts = new HashMap<>();
        int maxOverlap = 0;
        
        // Step 2 & 3: Calculate translation vectors and count frequencies
        for (int[] p1 : list1) {
            for (int[] p2 : list2) {
                // Format the vector as a string key "deltaRow,deltaCol"
                String vector = (p2[0] - p1[0]) + "," + (p2[1] - p1[1]);
                int count = vectorCounts.getOrDefault(vector, 0) + 1;
                vectorCounts.put(vector, count);
                
                // Step 4: Track the most frequent vector
                maxOverlap = Math.max(maxOverlap, count);
            }
        }
        
        return maxOverlap;
    }
}