import java.util.*;

class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        // Derive 'n' directly from the online array's length
        int n = online.length;
        
        // Step 1: Build the graph and calculate in-degrees
        List<int[]>[] adj = new ArrayList[n];
        int[] inDegree = new int[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        int maxCost = 0;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];
            
            adj[u].add(new int[]{v, cost});
            inDegree[v]++;
            maxCost = Math.max(maxCost, cost);
        }

        // Step 2: Compute Topological Sort using Kahn's Algorithm
        int[] topo = new int[n];
        int idx = 0;
        Queue<Integer> q = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }
        
        while (!q.isEmpty()) {
            int u = q.poll();
            topo[idx++] = u;
            for (int[] e : adj[u]) {
                int v = e[0];
                if (--inDegree[v] == 0) {
                    q.offer(v);
                }
            }
        }

        // Step 3: Binary Search on the maximum path score
        int low = 0;
        int high = maxCost;
        int ans = -1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (canReach(n, adj, topo, online, k, mid)) {
                ans = mid;       // This score works, save it
                low = mid + 1;   // Try to find a larger minimum edge
            } else {
                high = mid - 1;  // This score is too high, lower it
            }
        }
        
        return ans;
    }

    private boolean canReach(int n, List<int[]>[] adj, int[] topo, boolean[] online, long k, int minEdgeReq) {
        long[] dp = new long[n];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0; // Start at node 0

        // Process nodes in topological order
        for (int u : topo) {
            // If the node is unreachable or offline, skip it
            if (dp[u] == Long.MAX_VALUE || !online[u]) {
                continue;
            }
            
            for (int[] edge : adj[u]) {
                int v = edge[0];
                int cost = edge[1];
                
                // Only traverse if the edge meets our minimum score and the destination is online
                if (cost >= minEdgeReq && online[v]) {
                    if (dp[u] + cost < dp[v]) {
                        dp[v] = dp[u] + cost;
                    }
                }
            }
        }
        
        // Return true if we reached the destination within the allowed cost 'k'
        return dp[n - 1] <= k;
    }
}