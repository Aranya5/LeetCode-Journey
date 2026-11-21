class Solution {
    private List<Integer>[] origAdj;
    private List<Integer>[] newAdj;
    private int[] newParent;
    private int[] lastSeen;
    private String s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s;
        this.newParent = Arrays.copyOf(parent, n);
        this.lastSeen = new int[26];
        Arrays.fill(this.lastSeen, -1);

        // 1. Build adjacency list for the original tree
        origAdj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            origAdj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            origAdj[parent[i]].add(i);
        }

        // 2. Find the closest matching ancestor for each node via DFS
        findNewParents(0);

        // 3. Build adjacency list for the modified tree
        newAdj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            newAdj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            newAdj[newParent[i]].add(i);
        }

        // 4. Compute subtree sizes in the final tree
        int[] answer = new int[n];
        computeSizes(0, answer);
        return answer;
    }

    private void findNewParents(int u) {
        int charIdx = s.charAt(u) - 'a';
        int prevAncestor = lastSeen[charIdx];

        // If an ancestor with the same character exists, update u's parent
        if (prevAncestor != -1) {
            newParent[u] = prevAncestor;
        }

        // Set current node u as the closest ancestor for its character
        lastSeen[charIdx] = u;

        for (int v : origAdj[u]) {
            findNewParents(v);
        }

        // Backtrack: restore the previous ancestor before leaving u's subtree
        lastSeen[charIdx] = prevAncestor;
    }

    private int computeSizes(int u, int[] answer) {
        int size = 1;
        for (int v : newAdj[u]) {
            size += computeSizes(v, answer);
        }
        answer[u] = size;
        return size;
    }
}