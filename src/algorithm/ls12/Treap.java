package algorithm.ls12;

import java.util.Random;

public class Treap {

    public static void main(String[] args) {
        Treap treap = new Treap();
        treap.insert(1);
        treap.insert(2);
        treap.insert(3);
        treap.insert(4);
        treap.insert(5);

        System.out.println("從小到大的排序:");
        treap.inorderTraversal();
        System.out.println("\nTreap:");
        treap.printTree();
    }

    private TreapNode root;
    private Random random;

    public Treap() {
        root = null;
        random = new Random();
    }

    public void insert(int value) {
        root = insert(root, value);
    }

    private TreapNode insert(TreapNode node, int value) {
        if (node == null) {
            return new TreapNode(value, random.nextInt());
        }

        if (value < node.value) {
            node.left = insert(node.left, value);
            if (node.left.priority > node.priority) {
                node = rightRotate(node);
            }
        } else if (value > node.value) {
            node.right = insert(node.right, value);
            if (node.right.priority > node.priority) {
                node = leftRotate(node);
            }
        }

        return node;
    }

    private TreapNode rightRotate(TreapNode y) {
        TreapNode x = y.left;
        TreapNode T2 = x.right;

        x.right = y;
        y.left = T2;

        return x;
    }

    private TreapNode leftRotate(TreapNode x) {
        TreapNode y = x.right;
        TreapNode T2 = y.left;

        y.left = x;
        x.right = T2;

        return y;
    }

    public void inorderTraversal() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(TreapNode node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print(node.value + " ");
            inorderRec(node.right);
        }
    }

    public void printTree() {
        printTreeRec(root, 0);
    }

    private void printTreeRec(TreapNode root, int depth) {
        if (root == null) {
            return;
        }
        printTreeRec(root.right, depth + 1);
        System.out.println(" ".repeat(depth * 4) + root.value + "(" + root.priority + ")");
        printTreeRec(root.left, depth + 1);
    }

}

class TreapNode {
    int value;
    int priority;
    TreapNode left, right;

    public TreapNode(int value, int priority) {
        this.value = value;
        this.priority = priority;
        this.left = null;
        this.right = null;
    }
}
