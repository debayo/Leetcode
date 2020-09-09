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
 想法：如果中序遍历序列是有序的，那个这棵树是二叉搜索树
       那么问题就转化成非递归地得到中序遍历序列（栈着色法）
具体做法是: 
节点为空，返回null
否则加节点标成白色，入栈
每次迭代 取出一个节点，
若颜色为白色，则将右子树 节点（灰色） 左子树入栈
若颜色为黑色，则访问这个节点
当栈为空时地迭代终止                     
 */
class Solution {
    
   static class ColorTreeNode {
      TreeNode node;
      String color;
      ColorTreeNode(TreeNode node, String color) { this.node = node; this.color = color; }
  }

    public boolean isValidBST(TreeNode root) {
        if(root==null) return true;
        Stack<ColorTreeNode> stack = new Stack<>();
        stack.push(new ColorTreeNode(root, "WHITE"));

        Integer preVisited = null;
        while(!stack.isEmpty()){
            ColorTreeNode node = stack.pop();
            if(node.color.equals("WHITE")) {
                if(node.node.right!=null) stack.push(new ColorTreeNode(node.node.right, "WHITE"));
                stack.push(new ColorTreeNode(node.node, "GREY"));
                if(node.node.left!=null) stack.push(new ColorTreeNode(node.node.left, "WHITE")); 
            } else {
                if(preVisited != null && preVisited.intValue() >= node.node.val) {
                    return false;
                }
                preVisited = node.node.val;
            }
        }

        return true;
    }

    /*
 思路二:
 1.递归法给定节点，上界限，下界限（开区间），递归的求解
2. 迭代法 将上面的方法改造成先序遍历非递归形式（栈） 

PS 今天又练习了一遍这个递归转非递归，有一些心得了
先序 用栈很简单，
中序后序 用着色节点+栈来做
 */
    class Node {
        TreeNode node;
        Integer low;
        Integer high;
        Node(TreeNode node, Integer low, Integer high) {
            this.node = node;
            this.low = low;
            this.high = high;
        }
    }
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        Stack<Node> stack = new Stack<>();

        stack.push(new Node(root, null, null));
        while(!stack.isEmpty()) {
            Node top = stack.pop();
            TreeNode node = top.node;
            if(top.low!=null && node.val <= top.low) return false;
            if(top.high!=null && node.val >= top.high) return false;
            if(node.left != null) stack.push(new Node(node.left, top.low, node.val));
            if(node.right != null) stack.push(new Node(node.right, node.val, top.high));
        }
        return true;
    }
}
