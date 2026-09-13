class Pair {
    int first;
    int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

class Solution {
    private void bfs(int rw, int cl, char[][] grid, int[][] vis) {
        vis[rw][cl] = 1;

        Queue<Pair> q = new LinkedList<Pair>();
        q.add(new Pair(rw, cl));

        int n = grid.length;
        int m = grid[0].length;

        int[] delRow = { -1, 1, 0, 0 };
        int[] delCol = { 0, 0, -1, 1 };

        while (!q.isEmpty()) {
            int row = q.peek().first;
            int col = q.peek().second;
            q.remove();

            for (int i = 0; i < 4; i++) {
                int nRow = row + delRow[i];
                int nCol = col + delCol[i];

                if (nRow >= 0 && nRow < n &&
                        nCol >= 0 && nCol < m &&
                        grid[nRow][nCol] == '1' &&
                        vis[nRow][nCol] == 0) {

                    vis[nRow][nCol] = 1;
                    q.add(new Pair(nRow, nCol));
                }
            }
        }
    }

    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] vis = new int[n][m];

        int cnt = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (vis[row][col] == 0 && grid[row][col] == '1') {
                    cnt++;
                    bfs(row, col, grid, vis);
                }
            }
        }

        return cnt;

    }
}