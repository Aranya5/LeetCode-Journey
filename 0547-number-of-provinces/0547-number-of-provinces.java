class Solution {
    private void dfs(int node, int[][] isConnected, boolean[] vis) {
        vis[node] = true;
        for (int neighbor = 0; neighbor < isConnected.length; neighbor++) {
            if (isConnected[node][neighbor] == 1 && !vis[neighbor]) {
                dfs(neighbor, isConnected, vis);
            }
        }
    }

    public int findCircleNum(int[][] isConnected) {
        int v = isConnected.length;
        boolean[] vis = new boolean[v];
        int count = 0;

        for (int i = 0; i < v; i++) {
            if (!vis[i]) {
                count++;
                dfs(i, isConnected, vis);
            }
        }

        return count;
    }
}