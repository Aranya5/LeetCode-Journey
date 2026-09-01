import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int startR = -1, startC = -1;
        int litterCount = 0;
        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);
                if (ch == 'S') {
                    startR = i;
                    startC = j;
                } else if (ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }
        
        // If there is no litter to collect
        if (litterCount == 0) return 0;
        
        int targetMask = (1 << litterCount) - 1;
        
        // maxEnergy[r][c][mask] stores the maximum energy seen so far for (r, c, mask)
        int[][][] maxEnergy = new int[m][n][1 << litterCount];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(maxEnergy[i][j], -1);
            }
        }
        
        // Queue stores: [r, c, energy, mask, moves]
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startR, startC, energy, 0, 0});
        maxEnergy[startR][startC][0] = energy;
        
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int currEnergy = curr[2];
            int mask = curr[3];
            int moves = curr[4];
            
            if (mask == targetMask) {
                return moves;
            }
            
            // If out of energy and not standing on 'R', cannot make further moves
            if (currEnergy == 0) {
                continue;
            }
            
            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];
                
                // Bounds and obstacle check
                if (nr < 0 || nr >= m || nc < 0 || nc >= n || classroom[nr].charAt(nc) == 'X') {
                    continue;
                }
                
                int nextEnergy = currEnergy - 1;
                char nextCell = classroom[nr].charAt(nc);
                
                // Reset energy if stepping onto 'R'
                if (nextCell == 'R') {
                    nextEnergy = energy;
                }
                
                // Collect litter if applicable
                int nextMask = mask;
                if (nextCell == 'L' && litterId[nr][nc] != -1) {
                    nextMask |= (1 << litterId[nr][nc]);
                }
                
                // Prune if this state was already reached with >= energy in <= moves
                if (nextEnergy <= maxEnergy[nr][nc][nextMask]) {
                    continue;
                }
                
                maxEnergy[nr][nc][nextMask] = nextEnergy;
                queue.offer(new int[]{nr, nc, nextEnergy, nextMask, moves + 1});
            }
        }
        
        return -1;
    }
}