package ua.com.alevel;

import java.util.Random;
import java.util.Scanner;

class L2_T2_RandomBinaryTree {

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

class BinaryTreeRun {

    public void binTreeRun() {
        int selector = 1;
        int countNodes;
        int key;
        while (selector != 0) {
            L2_T2_RandomBinaryTree randomBinaryTree = new L2_T2_RandomBinaryTree();
            Solution solution = new Solution();
            Scanner scanner = new Scanner(System.in);

            System.out.println("Постройте дерево!\n" +
                    "введите количество узлов и значение узла соответсвенно:\n" +
                    "Вводите только цифры чтобы программа продолжала работу!");

            countNodes = scanner.nextInt();
            key = scanner.nextInt();

            int maxDeep = solution.maxDepth(randomBinaryTree.binaryTreeGenerator(countNodes, key));
            System.out.println("Максимальная глубина дерева:" + maxDeep);

            System.out.println("Решить задачу еще раз?\n" +
                    "1 - ДА\n" +
                    "0 - Вернуться в меню");
            selector = scanner.nextInt();
        }
    }
}
