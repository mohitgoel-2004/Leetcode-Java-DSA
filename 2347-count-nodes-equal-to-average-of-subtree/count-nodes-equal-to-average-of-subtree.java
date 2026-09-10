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
    private int matchingNodesCnt = 0;

    public int averageOfSubtree(TreeNode root) {
        postOrder(root);
        return matchingNodesCnt;
    }

    private int[] postOrder(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0}; // {sum, count}
        }

        int[] left = postOrder(node.left);
        int[] right = postOrder(node.right);

        int currSum = node.val + left[0] + right[0];
        int currCnt = 1 + left[1] + right[1];

        if (currSum / currCnt == node.val) {
            matchingNodesCnt++;
        }

        return new int[]{currSum, currCnt};
    }
}