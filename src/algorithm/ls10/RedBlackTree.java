package algorithm.ls10;

public class RedBlackTree {

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);

        System.out.println("從小到大的排序:");
        tree.inorderTraversal();
        System.out.println("\nRed Black Tree:");
        tree.printTree();
    }



    private Node root;

    public void insert(int data) {
        Node newNode = new Node(data);
        root = bstInsert(root, null, newNode);
        fixViolation(newNode);
    }

    private Node bstInsert(Node current, Node parent, Node newNode) {
        if (current == null) {
            newNode.parent = parent;
            return newNode;
        }

        if (newNode.data < current.data) {
            current.left = bstInsert(current.left, current, newNode);
        } else if (newNode.data > current.data) {
            current.right = bstInsert(current.right, current, newNode);
        }
        return current;
    }

    private void fixViolation(Node node) {
        while (node != root && node.parent.isRed) {
            Node parent = node.parent;
            Node grandparent = parent.parent;

            if (parent == grandparent.left) {
                Node uncle = grandparent.right;

                if (uncle != null && uncle.isRed) { // Case 1: Uncle is red
                    parent.isRed = false;
                    uncle.isRed = false;
                    grandparent.isRed = true;
                    node = grandparent;
                } else {
                    if (node == parent.right) { // Case 2: Node is right child
                        node = parent;
                        leftRotate(node);
                    }

                    parent.isRed = false; // Case 3: Recolor, rotate
                    grandparent.isRed = true;
                    rightRotate(grandparent);
                }
            } else {
                Node uncle = grandparent.left;

                if (uncle != null && uncle.isRed) { // Mirror Case 1
                    parent.isRed = false;
                    uncle.isRed = false;
                    grandparent.isRed = true;
                    node = grandparent;
                } else {
                    if (node == parent.left) { // Mirror Case 2
                        node = parent;
                        rightRotate(node);
                    }

                    parent.isRed = false; // Mirror Case 3
                    grandparent.isRed = true;
                    leftRotate(grandparent);
                }
            }
        }
        root.isRed = false; // 根節點一定是黑色
    }

    private void leftRotate(Node node) {
        Node rightChild = node.right;
        node.right = rightChild.left;

        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }

        rightChild.parent = node.parent;

        if (node.parent == null) {
            root = rightChild;
        } else if (node == node.parent.left) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }

        rightChild.left = node;
        node.parent = rightChild;
    }

    private void rightRotate(Node node) {
        Node leftChild = node.left;
        node.left = leftChild.right;

        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }

        leftChild.parent = node.parent;

        if (node.parent == null) {
            root = leftChild;
        } else if (node == node.parent.left) {
            node.parent.left = leftChild;
        } else {
            node.parent.right = leftChild;
        }

        leftChild.right = node;
        node.parent = leftChild;
    }

    public void inorderTraversal() {
        inorderRec(root);
    }

    private void inorderRec(Node node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print(node.data + (node.isRed ? "(紅) " : "(黑) "));
            inorderRec(node.right);
        }
    }

    private void printTree() {
        printTreeRec(root, 0);
    }

    private void printTreeRec(Node root, int depth) {
        if (root == null) {
            System.out.println(" ".repeat(depth * 4) + "null(黑)");
            return;
        }
        printTreeRec(root.right, depth + 1);
        System.out.println(" ".repeat(depth * 4) + root.data + (root.isRed ? "(紅)" : "(黑)"));
        printTreeRec(root.left, depth + 1);
    }
}

class Node {
    int data;
    Node left, right, parent;
    boolean isRed;

    Node(int data) {
        this.data = data;
        this.isRed = true;
    }
}