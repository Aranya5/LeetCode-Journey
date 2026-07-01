import java.util.*;

class Solution {
    // Defines movement: Down, Up, Right, Left
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        
        // Edge Case: If the start or end cell has a thief, the safeness factor is instantly 0.
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) {
            return 0;
        }

        // --- PHASE 1: Multi-Source BFS ---
        int[][] safeness = new int[n][n];
        for (int[] row : safeness) {
            Arrays.fill(row, -1); // -1 acts as our unvisited flag
        }
        
        Queue<int[]> queue = new LinkedList<>();

        // Add all thieves to the queue to start the simultaneous BFS
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid.get(r).get(c) == 1) {
                    queue.offer(new int[]{r, c});
                    safeness[r][c] = 0;
                }
            }
        }

        // Expand outward to map the danger distances
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int currentSafeness = safeness[r][c];

            for (int[] dir : DIRECTIONS) {
                int newRow = r + dir[0];
                int newCol = c + dir[1];

                // If within bounds and unvisited
                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n && safeness[newRow][newCol] == -1) {
                    safeness[newRow][newCol] = currentSafeness + 1;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }

        // --- PHASE 2: Max-Heap (Dijkstra's Variation) ---
        // Priority Queue ordered by highest bottleneck safeness descending
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[2], a[2]));
        
        boolean[][] visited = new boolean[n][n];
        
        // Start at (0, 0)
        maxHeap.offer(new int[]{0, 0, safeness[0][0]});
        visited[0][0] = true;

        while (!maxHeap.isEmpty()) {
            int[] curr = maxHeap.poll();
            int r = curr[0];
            int c = curr[1];
            int bottleneck = curr[2];

            // If we reached the destination, we found the optimal safe path!
            if (r == n - 1 && c == n - 1) {
                return bottleneck;
            }

            for (int[] dir : DIRECTIONS) {
                int newRow = r + dir[0];
                int newCol = c + dir[1];

                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n && !visited[newRow][newCol]) {
                    visited[newRow][newCol] = true;
                    // The path's safety is constrained by the most dangerous cell encountered
                    int newBottleneck = Math.min(bottleneck, safeness[newRow][newCol]);
                    maxHeap.offer(new int[]{newRow, newCol, newBottleneck});
                }
            }
        }

        return 0; 
    }
}