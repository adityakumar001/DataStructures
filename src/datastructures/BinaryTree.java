package datastructures;

import interfaces.DataStructure;
import interfaces.List;

public abstract class BinaryTree<T> implements DataStructure<T> {

    public static final String IN_ORDER = "In Order", POST_ORDER = "Post Order", PRE_ORDER = "Pre Order",
            LEVEL_ORDER = "Level Order";

    Node<T> root;

    public List<T> getElements() {
        return getElements(IN_ORDER);
    }

    public List<T> getElements(String order) {
        List<T> elements = new DynamicArray<>();
        switch (order) {
            case IN_ORDER:
                getTreeInOrder(root, elements);
                break;
            case PRE_ORDER:
                getTreePreOrder(root, elements);
                break;
            case POST_ORDER:
                getTreePostOrder(root, elements);
                break;
            case LEVEL_ORDER:
                getTreeLevelOrder(root, elements);
                break;
        }
        return elements;
    }

    public void getTreeInOrder(Node<T> parent, List<T> elements) {
        if (parent == null) return;
        getTreeInOrder(parent.left, elements);
        elements.insert(parent.data);
        getTreeInOrder(parent.right, elements);

    }

    public void getTreePreOrder(Node<T> parent, List<T> elements) {
        if (parent == null) return;
        elements.insert(parent.data);
        getTreePreOrder(parent.left, elements);
        getTreePreOrder(parent.right, elements);
    }

    public void getTreePostOrder(Node<T> parent, List<T> elements) {
        if (parent == null) return;
        getTreePostOrder(parent.left, elements);
        getTreePostOrder(parent.right, elements);
        elements.insert(parent.data);
    }

    public void getTreeLevelOrder(Node<T> parent, List<T> elements) {
        if (parent == null) {
            return;
        }
        interfaces.Queue<Node<T>> queue = new datastructures.DynamicArray<>();
        queue.enqueue(parent);
        while (queue.size() > 0) {
            Node<T> current = queue.dequeue();
            elements.insert(current.data);
            if (current.left != null) queue.enqueue(current.left);
            if (current.right != null) queue.enqueue(current.right);
        }
    }

    public int maxHeight() {
        if (root == null) {
            return -1;
        }
        return findHeight(root);
    }

    protected int findHeight(Node<T> parent) {
        if (parent == null) {
            return 0;
        }
        int leftHeight = findHeight(parent.left);
        int rightHeight = findHeight(parent.right);
        return Integer.max(leftHeight, rightHeight) + 1;
    }

    public static class Node<T> {

        Node<T> left, right;
        T data;

        private int lHeight, rHeight;
        private boolean balanced;

        Node(T data) {
            this.data = data;
        }

        public int getLHeight() {
            return lHeight;

        }

        public void setLHeight(int lHeight) {
            this.lHeight = lHeight;
        }

        public int getRHeight() {
            return rHeight;
        }

        public void setRHeight(int rHeight) {
            this.rHeight = rHeight;
        }

        public int balance() {
            int balance = rHeight - lHeight;
            if (balance < -1) {
                return -1;
            } else if (balance > 1) return 1;
            else return 0;
        }

        boolean isLeaf() {
            return left == null && right == null;
        }

    }
}
