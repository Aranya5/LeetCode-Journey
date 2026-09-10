/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        return dfs(root, 0, limit);
    }

    private TreeNode dfs(TreeNode node, int currentSum, int limit) {
        if (node == null) {
            return null;
        }

        currentSum += node.val;

        // Base case: If it's a leaf node, validate the full path sum
        if (node.left == null && node.right == null) {
            if (currentSum < limit) {
                return null; // Delete this leaf
            }
            return node; // Keep this leaf
        }

        // Post-order traversal: evaluate children first
        node.left = dfs(node.left, currentSum, limit);
        node.right = dfs(node.right, currentSum, limit);

        // If both children were deleted, all paths through this node failed
        if (node.left == null && node.right == null) {
            return null; // Delete this parent node
        }

        return node; // At least one valid path survived, keep this node
    }
}