package tree;

import java.util.Stack;

/**
 * @author BlackSugar
 * @date 2019/4/19
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * <p>
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 * But the following [1,2,2,null,3,null,3] is not:
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
 */
public class SymmetricTree {
    /**
     * 对称树
     * 思路：既然题目要求了用递归和迭代两种方式
     * 1、递归，每个节点的左节点等于对称节点的右节点
     * 2、迭代，利用栈（或者队列、链表都可以），将对应的节点依次放入栈中并依次弹出做比较
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        /*return null == root
                || null == root.left && null == root.right
                || (root.left != null && root.right != null) && isSymmetricHelper(root.left, root.right);*/

        if (null == root) {
            return true;
        }
        TreeNode left = root.left, right = root.right;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(left);
        stack.push(right);
        while (!stack.empty()) {
            right = stack.pop();
            left = stack.pop();
            if (null == left && null == right) {
                continue;
            }
            if (left == null || right == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }
            stack.push(left.left);
            stack.push(right.right);
            stack.push(left.right);
            stack.push(right.left);
        }
        return true;
    }

    private boolean isSymmetricHelper(TreeNode left, TreeNode right) {
        //base case
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        } else if (left.val != right.val) {
            return false;
        }
        return isSymmetricHelper(left.left, right.right) && isSymmetricHelper(left.right, right.left);
    }


    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(2);
        treeNode.left.left = new TreeNode(3);
        treeNode.left.right = new TreeNode(4);
        treeNode.right.left = new TreeNode(4);
        treeNode.right.right = new TreeNode(3);
        System.out.println(new SymmetricTree().isSymmetric(treeNode));
    }
}
