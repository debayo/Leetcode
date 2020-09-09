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
思路:
1.空数组 返回null
2.数组元素个数等于1, 返回根据该值构造的节点
3.数组元素个数大于1, 先根据先序遍历序列的首元素,在中序遍历序列中找到相同值的元素。
  这个元素将中序遍历序列分成了三部分，中间部分根据该值构造根节点，左边部分对应左子树，右边部分对应右子树;
  相应的也将先序遍历序列分成了三部分。第一个部分对应根节点，第二部分对应左子树，第三部分对应右子树;
  接下来递归的处理 中序遍历序列的左子树和先序遍历序列的左子树，中序遍历序列的右子树和先序遍历序列的右子树
*/
class Solution {
    public TreeNode buildTreeCore(int[] preorder, int s1, int e1, int[] inorder, int s2 ,int e2, HashMap<Integer, Integer> map ) {
        if(s1>e1) {
            return null;
        }
        if(s1 == e1) {
            return new TreeNode(preorder[s1]);
        }
        //speedup by hashset
        int index = map.get(preorder[s1]);
        //for(index=s2; index<=e2; ++index) if(inorder[index] == preorder[s1]) break;

        TreeNode rootNode = new TreeNode(preorder[s1]);
        int length = index-s2;
        rootNode.left = buildTreeCore(preorder, s1+1, s1+length, inorder, s2, index-1, map);
        rootNode.right = buildTreeCore(preorder, s1+length+1, e1, inorder, index+1, e2, map);
        return rootNode;
    }    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<inorder.length; ++i) map.put(inorder[i],i);
        return buildTreeCore(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1,map);
    }
}
