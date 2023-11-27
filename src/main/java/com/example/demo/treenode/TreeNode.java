package com.example.demo.treenode;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    // 前序遍历
    public void preorderTraversal(TreeNode root) {
        if (root != null) {
            System.out.println(root.val);
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
    }

    public void inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.println(root.val);
            inorderTraversal(root.right);
        }
    }

    public void postorderTraversal(TreeNode root) {
        if (root != null) {
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            System.out.println(root.val);
        }
    }

    public static void main(String[] args) {

    }
}