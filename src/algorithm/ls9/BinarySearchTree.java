package algorithm.ls9;

import java.util.Random;

public class BinarySearchTree {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, 8, 2, 6, 7, 5};


        BinarySearchTree bst = new BinarySearchTree();
        for(int val : arr) {
            bst.insert(val);
        }

        System.out.println("從小到大的排序:");
        bst.printInOrder();

        System.out.println("Binary Search Tree:");
        bst.printTree();

        System.out.println("\n搜尋節點 3: " + (bst.search(3) ? "存在" : "不存在"));
        System.out.println("搜尋節點 9: " + (bst.search(9) ? "存在" : "不存在"));

        System.out.println("\n刪除節點 3:");
        bst.delete(3);
        bst.printTree();

        randomArray(arr);
        BinarySearchTree bst2 = new BinarySearchTree();
        for(int val : arr) {
            bst2.insert(val);
        }

        System.out.println("Random Binary Search Tree:");
        bst2.printTree();



    }


    private Node root;

    private void insert(int value) {
        root = insertRec(root, value);
    }

    private Node insertRec(Node root, int value) {
        if (root == null) {
            return new Node(value);
        }
        if (value < root.value) {
            root.left = insertRec(root.left, value);
        } else if (value > root.value) {
            root.right = insertRec(root.right, value);
        }
        return root;
    }

    private void delete(int value) {
        root = deleteRec(root, value);
    }

    private Node deleteRec(Node root, int value) {
        if (root == null) {
            return null;
        }

        if (value < root.value) {
            root.left = deleteRec(root.left, value);
        } else if (value > root.value) {
            root.right = deleteRec(root.right, value);
        } else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            root.value = findMin(root.right);
            root.right = deleteRec(root.right, root.value);
        }

        return root;
    }

    private int findMin(Node root) {
        int minValue = root.value;
        while (root.left != null) {
            root = root.left;
            minValue = root.value;
        }
        return minValue;
    }

    private boolean search(int value) {
        return searchRec(root, value) != null;
    }

    private Node searchRec(Node root, int value) {
        if (root == null || root.value == value) {
            return root;
        }
        if (value < root.value) {
            return searchRec(root.left, value);
        }
        return searchRec(root.right, value);
    }

    private void printTree() {
        printTreeRec(root, 0);
    }

    private void printTreeRec(Node root, int depth) {
        if (root == null) {
            return;
        }
        printTreeRec(root.right, depth + 1);
        System.out.println(" ".repeat(depth * 4) + root.value);
        printTreeRec(root.left, depth + 1);
    }

    public void printInOrder() {
        printInOrderRec(root);
        System.out.println();
    }

    private void printInOrderRec(Node root) {
        if (root != null) {
            printInOrderRec(root.left);
            System.out.print(root.value + " ");
            printInOrderRec(root.right);
        }
    }

    private static void randomArray(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}

class Node {
    int value;
    Node left, right;

    Node(int value) {
        this.value = value;
        left = right = null;
    }
}