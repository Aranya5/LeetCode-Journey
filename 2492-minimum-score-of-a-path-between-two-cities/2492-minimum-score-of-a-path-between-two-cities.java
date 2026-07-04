import java.util.*;

class Solution {
    public int minScore(int n, int[][] roads) {
        // Step 1: Build the adjacency list for the undirected graph
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int distance = road[2];
            
            // Add bidirectional edges (neighbor, distance)
            adj.get(u).add(new int[]{v, distance});
            adj.get(v).add(new int[]{u, distance});
        }
        
        // Step 2: Set up the BFS
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        
        queue.offer(1);
        visited[1] = true;
        
        int minScore = Integer.MAX_VALUE;
        
        // Step 3: Traverse the connected component
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            
            for (int[] edge : adj.get(curr)) {
                int nextCity = edge[0];
                int dist = edge[1];
                
                // Always check the edge distance, even if the node is already visited
                minScore = Math.min(minScore, dist);
                
                if (!visited[nextCity]) {
                    visited[nextCity] = true;
                    queue.offer(nextCity);
                }
            }
        }
        
        return minScore;
    }
}