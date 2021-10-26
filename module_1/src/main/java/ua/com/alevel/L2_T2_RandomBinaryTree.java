package ua.com.alevel;
import java.util.Random;

public class L2_T2_RandomBinaryTree {
    private Random random = new Random();

    public TreeNode binaryTreeGenerator(int n, int key) {
        if (n == 0)
            return null;

        TreeNode root = new TreeNode(key);

        // Number of nodes in the left subtree (in [0, n-1])
        int leftN = random.nextInt(n);

        // Recursively build each subtree
        root.setLeft(binaryTreeGenerator(leftN, key));
        root.setRight(binaryTreeGenerator(n - leftN - 1, key));

        return root;
    }
}

class TreeNode {
    int key;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        key = x;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}

class Solution {

    public int maxDepth(TreeNode root) {

        if (root == null) return 0;

        int Left = maxDepth(root.left);

        int Right = maxDepth(root.right);

        return Math.max(Left, Right) + 1;
    }
}
