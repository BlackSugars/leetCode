package tree;

/**
 * @author BlackSugar
 * @date 2019/4/22
 * Given a binary tree, find its maximum depth.
 * <p>
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * <p>
 * Note: A leaf is a node with no children.
 * <p>
 * Example:
 * <p>
 * Given binary tree [3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its depth = 3.
 */
public class MaximumDepthOfBinaryTree {
    /**
     * 获取数深度
     * 思路：递归，找最大深度，或者利用栈、队列迭代，和SymmetricTree差不多的做法，就不写了 :)
     * 大神递归代码：
     * <p>
     * if (null == root) {
     * return 0;
     * }
     * return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        return maxDepthHelper(root, 1);
    }

    private int maxDepthHelper(TreeNode node, int depth) {
        if (node.left == null && node.right == null) {
            return depth;
        } else if (node.left == null) {
            return maxDepthHelper(node.right, depth + 1);
        } else if (node.right == null) {
            return maxDepthHelper(node.left, depth + 1);
        }
        return Math.max(maxDepthHelper(node.left, depth + 1), maxDepthHelper(node.right, depth + 1));
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(2);
        treeNode.left.left = new TreeNode(3);
        treeNode.left.right = new TreeNode(4);
        treeNode.right.left = new TreeNode(4);
        treeNode.right.right = new TreeNode(3);

        System.out.println(new MaximumDepthOfBinaryTree().maxDepth(treeNode));
    }
}
