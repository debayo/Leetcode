/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode buildTreeCore(int[] preorder, int s1, int e1, int[] inorder, int s2 ,int e2) {
        if(s1>e1) {
            return null;
        }
        if(s1 == e1) {
            return new TreeNode(preorder[s1]);
        }
        int index;
        for(index=s2; index<=e2; ++index) if(inorder[index] == preorder[s1]) break;
        TreeNode rootNode = new TreeNode(preorder[s1]);
        rootNode.left = buildTreeCore(preorder, s1+1, s1+(index-s2), inorder, s2, index-1);
        rootNode.right = buildTreeCore(preorder, s1+(index-s2)+1, e1, inorder, index+1, e2);
        return rootNode;
    }    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeCore(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }
}
