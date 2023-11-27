package com.example.demo.treenode;

public class RedBlackTree<T extends Comparable<T>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        T key;
        V value;
        Node left;
        Node right;
        Node parent;
        boolean color;

        Node(T key, boolean color) {
            this.key = key;
            this.color = color;
        }
    }

    private Node root;

    public void insert(T key) {
        root = insert(root, key);
        root.color = BLACK;
    }

    private Node insert(Node node, T key) {
        if (node == null) {
            return new Node(key, RED);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = insert(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            node.right = insert(node.right, key);
        } else {
            node.key = key;
        }

        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }

        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }

        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    public boolean contains(T key) {
        Node node = getNode(root, key);
        return node != null && node.key.equals(key);
    }

    private Node getNode(Node node, T key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return getNode(node.right, key);
        } else {
            return node;
        }
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return node.color == RED;
    }

    private Node rotateLeft(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    private Node rotateRight(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }
//    boolean isRed(Node node) {
//        return node.color == RED;
//    }
//
//    public Node rotateRight(Node node) {
//        Node left = node.left;
//        Node right = node.left.right;
//        node.left = right;
//        node.right = left;
//        left.right = node;
//        return node;
//    }
//
//    public Node rotateLeft(Node node) {
//        Node right = node.right;
//        node.right = right.left;
//        right.left = node;
//
//        return node;
//    }
//
//    public void flipColors(Node node) {
//        node.color = RED;
//        node.left.color = BLACK;
//        node.right.color = BLACK;
//        node.parent.color = RED;
//    }
}
