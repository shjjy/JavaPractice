package ds.hw11;

public class BST {

    public static void main(String[] args) {
        int[] a = new int[]{3, 3, 5, 9, 5, 3, 4, 8, 5, 3, 10, 10, 8};
        int[] b = new int[]{10, 7, 12, 8, 11, 4};
        Node nodeA = doHomeWork(a);
        printTree(nodeA);
        System.out.println();
        Node nodeB = doHomeWork(b);
        printTree(nodeB);
        System.out.println();

        int[] c = new int[]{7, 2, 8, 1, 5, 3, 6, 4};
        Node nodeC = doHomeWork(c);
        printTree(nodeC);
        System.out.println();

        int[] d = new int[]{7, 2, 8, 1, 5, 3, 6, 4, 2};
        Node nodeD = doHomeWork(d);
        printTree(nodeD);
    }

    private static Node doHomeWork(int[] req) {
        if (req.length == 0) return null;
        Node node = null;
        for (int i : req) {
            node = visit(node, i);
        }
        return node;
    }

    private static Node visit(Node node, int i) {
        if (node == null) return new Node(i);
        if (node.data > i) {
            node.left = visit(node.left, i);
        } else if (node.data < i) {
            node.right = visit(node.right, i);
        } else {
            node = delete(node, i);
        }
        return node;
    }

    private static Node delete(Node node, int value) {
        if (value < node.data) {
            node.left = delete(node.left, value);
        } else if (value > node.data) {
            node.right = delete(node.right, value);
        } else {
            if (node.left == null && node.right == null) {
                return null;
            } else if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node successor = getRightMinNode(node.right);
                node.data = successor.data;
                node.right = delete(node.right, successor.data);
            }
        }
        return node;
    }

    private static Node getRightMinNode(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    private static void printTree(Node node) {
        int size = getSize(node);
        int[] nodeArr = new int[size];
        int[] leftArr = new int[size];
        int[] rightArr = new int[size];
        int[] index = new int[1];
        storeToArray(node, nodeArr, leftArr, rightArr, index);
        System.out.print("Node: ");
        for (int i = 0; i < size; i++) {
            System.out.print(nodeArr[i] + " ");
        }
        System.out.println();

        System.out.print("Left: ");
        for (int i = 0; i < size; i++) {
            System.out.print(leftArr[i] + " ");
        }
        System.out.println();

        System.out.print("Right: ");
        for (int i = 0; i < size; i++) {
            System.out.print(rightArr[i] + " ");
        }
        System.out.println();
    }

    private static void storeToArray(Node node, int[] nodeArr, int[] leftArr, int[] rightArr, int[] index) {
        if (node == null) return;
        storeToArray(node.left, nodeArr, leftArr, rightArr, index);

        nodeArr[index[0]] = node.data;
        leftArr[index[0]] = (node.left != null) ? node.left.data : 0;
        rightArr[index[0]] = (node.right != null) ? node.right.data : 0;
        index[0]++;

        storeToArray(node.right, nodeArr, leftArr, rightArr, index);
    }

    private static int getSize(Node node) {
        if (node == null) return 0;
        return 1 + getSize(node.left) + getSize(node.right);
    }

}

class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

}
