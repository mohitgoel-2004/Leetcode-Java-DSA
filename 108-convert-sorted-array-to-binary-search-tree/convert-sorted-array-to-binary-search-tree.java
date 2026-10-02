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
    public TreeNode sortedArrayToBST(int[] nums) {
     return build(nums, 0, nums.length-1);   
    }

    private TreeNode build(int[] nums, int s, int e){
        if(s>e){
            return null;
        }
        int mid = s + (e - s)/2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = build(nums, s, mid - 1);
        root.right = build(nums, mid + 1, e);

        return root;
    }
}