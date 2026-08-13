class Solution {
    class Node {
        int size, maxLen, prefLen, suffLen;
        char prefChar, suffChar;
    }

    Node[] tree;
    char[] arr;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        arr = s.toCharArray();
        tree = new Node[4 * n];
        
        for (int i = 0; i < 4 * n; i++) {
            tree[i] = new Node();
        }

        // 1. Build the initial segment tree
        build(0, 0, n - 1);

        int k = queryIndices.length;
        int[] ans = new int[k];

        // 2. Process each query
        for (int i = 0; i < k; i++) {
            update(0, 0, n - 1, queryIndices[i], queryCharacters.charAt(i));
            // The root node always contains the max length for the entire string
            ans[i] = tree[0].maxLen; 
        }

        return ans;
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node].size = 1;
            tree[node].maxLen = 1;
            tree[node].prefLen = 1;
            tree[node].suffLen = 1;
            tree[node].prefChar = arr[start];
            tree[node].suffChar = arr[start];
            return;
        }

        int mid = start + (end - start) / 2;
        int leftChild = 2 * node + 1;
        int rightChild = 2 * node + 2;

        build(leftChild, start, mid);
        build(rightChild, mid + 1, end);
        
        merge(tree[node], tree[leftChild], tree[rightChild]);
    }

    private void update(int node, int start, int end, int idx, char val) {
        if (start == end) {
            tree[node].prefChar = val;
            tree[node].suffChar = val;
            arr[idx] = val;
            return;
        }

        int mid = start + (end - start) / 2;
        int leftChild = 2 * node + 1;
        int rightChild = 2 * node + 2;

        if (idx <= mid) {
            update(leftChild, start, mid, idx, val);
        } else {
            update(rightChild, mid + 1, end, idx, val);
        }

        merge(tree[node], tree[leftChild], tree[rightChild]);
    }

    private void merge(Node parent, Node left, Node right) {
        parent.size = left.size + right.size;
        parent.prefChar = left.prefChar;
        parent.suffChar = right.suffChar;

        // Calculate Prefix Length
        parent.prefLen = left.prefLen;
        if (left.prefLen == left.size && left.prefChar == right.prefChar) {
            parent.prefLen += right.prefLen;
        }

        // Calculate Suffix Length
        parent.suffLen = right.suffLen;
        if (right.suffLen == right.size && right.suffChar == left.suffChar) {
            parent.suffLen += left.suffLen;
        }

        // Calculate Max Length (Standard left max, right max, or the bridge between them)
        parent.maxLen = Math.max(left.maxLen, right.maxLen);
        if (left.suffChar == right.prefChar) {
            parent.maxLen = Math.max(parent.maxLen, left.suffLen + right.prefLen);
        }
    }
}