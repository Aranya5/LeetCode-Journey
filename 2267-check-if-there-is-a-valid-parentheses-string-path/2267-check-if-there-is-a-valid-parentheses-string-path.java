class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // A valid parentheses string must have an even length
        int pathLength = m + n - 1;
        if (pathLength % 2 != 0) {
            return false;
        }
        
        // Quick boundary checks: must start with '(' and end with ')'
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }
        
        // The maximum open brackets we can afford to carry is half the path length
        int maxBalance = pathLength / 2;
        boolean[][][] visited = new boolean[m][n][maxBalance + 1];
        
        return dfs(grid, 0, 0, 0, visited, maxBalance);
    }
    
    private boolean dfs(char[][] grid, int r, int c, int balance, boolean[][][] visited, int maxBalance) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Update balance based on current cell
        balance += (grid[r][c] == '(') ? 1 : -1;
        
        // Invalid state: more closing brackets than opening, or too many opening brackets
        if (balance < 0 || balance > maxBalance) {
            return false;
        }
        
        // Reached destination: check if perfectly balanced
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }
        
        // If we have already explored this cell with this exact balance, it's a dead end
        if (visited[r][c][balance]) {
            return false;
        }
        
        // Mark current state as visited
        visited[r][c][balance] = true;
        
        // Explore Right
        if (c + 1 < n && dfs(grid, r, c + 1, balance, visited, maxBalance)) {
            return true;
        }
        
        // Explore Down
        if (r + 1 < m && dfs(grid, r + 1, c, balance, visited, maxBalance)) {
            return true;
        }
        
        return false;
    }
}