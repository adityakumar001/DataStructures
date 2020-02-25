package datastructures;

import interfaces.DataComparator;

public class BinarySearchTree<T> extends BinaryTree<T> {

    /*
     * The comparator is used to build the binary search tree.
     * */
    private final DataComparator<T> comparator;

    /**
     * Creates a Binary Search Tree with the the value provided as root and the comparator provided
     * is used to build the tree.
     */
    public BinarySearchTree(T data, DataComparator<T> comparator) {
        root = new Node<T>(data);
        this.comparator = comparator;
    }

    public BinarySearchTree(DataComparator<T> comparator) {
        this.comparator = comparator;
    }

    public BinarySearchTree(DataComparator<T> comparator, T... values) {
        this.comparator = comparator;
        for (T value : values) {
            insert(value);
        }
    }

    public T delete(T data) {
        if (comparator.compare(data, root.data) == 0) {
            if (root.left == null) {
                if (root.right == null) {
                    root = null;
                } else {
                    root = root.right;
                }
            } else if (root.right == null) {
                root = root.left;
            } else {
                Node<T> current = root.right, parent = root;
                while (current.left != null) {
                    parent = current;
                    current = current.left;
                }
                if (current.isLeaf()) {
                    parent.left = null;
                    current.left = root.left;
                    current.right = root.right;
                    root = current;
                } else {
                    parent.left = current.right;
                    current.left = root.left;
                    current.right = root.right;
                    root = current;
                }
            }
        }

        delete(root, data, root);
        return data;
    }

    private void delete(Node<T> current, T data, Node<T> parent) {
        int comparison = comparator.compare(data, current.data);
        if (comparison < 0) {
            delete(current.left, data, current);
        } else if (comparison > 0) {
            delete(current.right, data, current);
        } else {
            boolean noLeftChild = current.left == null, noRightChild = current.right == null,
                    toLeftOfParent = parent.left == current;
            if (current.isLeaf()) {
                if (toLeftOfParent)
                    parent.left = null;
                else parent.right = null;
            } else if (noLeftChild) {
                if (toLeftOfParent) {
                    parent.left = current.right;
                } else parent.right = current.right;
            } else if (noRightChild) {
                if (toLeftOfParent) {
                    parent.left = current.left;
                } else parent.right = current.left;
            } else {

                Node<T> inorderSuccessor = current.right, inOrderParent = current;

                while (inorderSuccessor.left != null) {
                    inOrderParent = inorderSuccessor;
                    inorderSuccessor = inorderSuccessor.left;
                }
                if (inorderSuccessor.isLeaf()) {
                    if (inOrderParent.left == inorderSuccessor)
                        inOrderParent.left = null;
                    else inOrderParent.right = null;
                    if (toLeftOfParent) {
                        parent.left = inorderSuccessor;
                    } else parent.right = inorderSuccessor;
                    inorderSuccessor.left = current.left;
                    inorderSuccessor.right = current.right;
                } else {
                    inOrderParent.left = inorderSuccessor.right;
                    inorderSuccessor.left = current.left;
                    inorderSuccessor.right = current.right;
                    if (toLeftOfParent)
                        parent.left = inorderSuccessor;
                    else parent.right = inorderSuccessor;
                }
            }
        }
    }

    public T minimum() {
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.data;
    }

    public T maximum() {
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.data;
    }

    /**
     * Returns the level at which the element is found else returns -1.
     */
    public int search(T item) {
        return search(item, root, 0);
    }

    private int search(T item, Node<T> parent, int level) {
        if (parent != null) {
            if (comparator.compare(item, parent.data) == 0) {
                return level;
            } else if (comparator.compare(item, parent.data) < 0) {
                return search(item, parent.left, ++level);
            } else if (comparator.compare(item, parent.data) > 0) {
                return search(item, parent.right, ++level);
            }
        }
        return -1;
    }

    public void insert(T item) {
        if (root == null) {
            root = new Node<>(item);
            return;
        }
        insert(item, root);
    }

    public Node<T> insert(T item, Node<T> parent) {
        if (parent == null) {
            parent = new Node<>(item);
        } else if (comparator.compare(item, parent.data) < 0) {
            parent.left = insert(item, parent.left);
        } else {
            parent.right = insert(item, parent.right);
        }
        return parent;
    }

}
