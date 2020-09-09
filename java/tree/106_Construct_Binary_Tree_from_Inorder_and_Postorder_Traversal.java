/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 /*
 /*
思路:
1.空数组 返回null
2.先根据先序遍历序列的首元素,在中序遍历序列中找到相同值的元素。
  这个元素将中序遍历序列分成了三部分，分别对应左子树 根节点 右子树 
  相应的也将后序遍历序列分成了三部分。分别对应左子树 右子树 根节点
  接下来递归地处理 中序遍历序列的左子树和先序遍历序列的左子树，中序遍历序列的右子树和先序遍历序列的右子树
*/
class Solution {
    private TreeNode buildTreeHelper(int []inorder, int inStart, int inEnd, int []postorder, int postStart, int postEnd, Map<Integer,Integer> map){
        if(inStart > inEnd || postStart > postEnd) return null;
        TreeNode root = new TreeNode(postorder[postEnd]);
        int index = map.get(postorder[postEnd]);
        root.left = buildTreeHelper(inorder, inStart, index-1, postorder, postStart, postStart+index-inStart-1, map);
        root.right = buildTreeHelper(inorder, index+1, inEnd, postorder, postStart+index-inStart, postEnd-1, map);
        return root;
    }
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<postorder.length; ++i) map.put(inorder[i],i);
        return buildTreeHelper(inorder,0,inorder.length-1,postorder,0,postorder.length-1,map);
    }
}
