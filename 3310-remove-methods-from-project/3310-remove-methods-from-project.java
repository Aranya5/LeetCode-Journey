import java.util.*;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        // Step 1: Build the adjacency list for the directed graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] inv : invocations) {
            graph.get(inv[0]).add(inv[1]);
        }
        
        // Step 2: Run BFS to find all suspicious methods
        boolean[] isSuspicious = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        
        queue.offer(k);
        isSuspicious[k] = true;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbor : graph.get(current)) {
                if (!isSuspicious[neighbor]) {
                    isSuspicious[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
        
        // Step 3: Check the perimeter (Are there any outside dependencies?)
        for (int[] inv : invocations) {
            int caller = inv[0];
            int callee = inv[1];
            
            // If a clean method calls a suspicious method, we cannot remove the group
            if (!isSuspicious[caller] && isSuspicious[callee]) {
                // Return all methods from 0 to n - 1
                List<Integer> allMethods = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    allMethods.add(i);
                }
                return allMethods;
            }
        }
        
        // Step 4: If perfectly isolated, return only the clean methods
        List<Integer> remaining = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!isSuspicious[i]) {
                remaining.add(i);
            }
        }
        
        return remaining;
    }
}