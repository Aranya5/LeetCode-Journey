class Solution {
    static class Node {
        int[] remain = new int[5];
        int prod = 1;
        boolean isEmpty = true;

        Node() {}

        Node(int val, int k) {
            val %= k;
            remain[val] = 1;
            prod = val;
            isEmpty = false;
        }
    }

    private Node[] tree;
    private int n;
    private int k;

    private Node merge(Node left, Node right) {
        if (left.isEmpty) return right;
        if (right.isEmpty) return left;

        Node res = new Node();
        res.isEmpty = false;
        res.prod = (left.prod * right.prod) % k;

        // Copy remainders from the left segment
        for (int i = 0; i < k; i++) {
            res.remain[i] += left.remain[i];
        }

        // Add remainders extending into the right segment
        for (int i = 0; i < k; i++) {
            int newRem = (i * left.prod) % k;
            res.remain[newRem] += right.remain[i];
        }

        return res;
    }

    private void build(int[] nums, int node, int l, int r) {
        if (l == r) {
            tree[node] = new Node(nums[l], k);
            return;
        }
        int mid = l + (r - l) / 2;
        build(nums, 2 * node + 1, l, mid);
        build(nums, 2 * node + 2, mid + 1, r);
        tree[node] = merge(tree[2 * node + 1], tree[2 * node + 2]);
    }

    private void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            tree[node] = new Node(val, k);
            return;
        }
        int mid = l + (r - l) / 2;
        if (idx <= mid) {
            update(2 * node + 1, l, mid, idx, val);
        } else {
            update(2 * node + 2, mid + 1, r, idx, val);
        }
        tree[node] = merge(tree[2 * node + 1], tree[2 * node + 2]);
    }

    private Node query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return tree[node];
        }
        int mid = l + (r - l) / 2;
        Node leftRes = new Node();
        Node rightRes = new Node();

        if (ql <= mid) {
            leftRes = query(2 * node + 1, l, mid, ql, qr);
        }
        if (qr > mid) {
            rightRes = query(2 * node + 2, mid + 1, r, ql, qr);
        }

        return merge(leftRes, rightRes);
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;
        this.tree = new Node[4 * n];

        build(nums, 0, 0, n - 1);

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(0, 0, n - 1, idx, val);

            Node res = query(0, 0, n - 1, start, n - 1);
            result[i] = res.remain[x];
        }

        return result;
    }
}