import datastructures.BinarySearchTree;
import datastructures.BinaryTree;


public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(Integer::compare,
                50, 48, 70, 30, 65, 90, 20, 32, 67, 98, 15, 25, 31, 35, 66, 69, 84, 99);
        System.out.println("InOrder : " + bst.getElements(BinaryTree.IN_ORDER));
        bst.delete(30);
        System.out.println("In Order : " + bst.getElements(BinaryTree.IN_ORDER));
    }
}

