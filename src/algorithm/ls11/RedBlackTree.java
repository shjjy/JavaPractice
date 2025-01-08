package algorithm.ls11;

public class RedBlackTree {

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);
        tree.insert(10);
        tree.insert(12);

        System.out.println("從小到大的排序:");
        tree.inorderTraversal();
        System.out.println("\nRed Black Tree:");
        tree.printTree();

        int k = 4;
        Node result = tree.osSelect(tree.root, k);
        System.out.println("第 " + k + " 小的元素是：" + result.data + (result.isRed ? "(紅)" : "(黑)"));

        int element = 12;  // 要查詢的元素
        int rank = tree.osRank(tree.root, element);
        System.out.println("元素 " + element + " 的排名是：" + rank);
    }

    private Node root;

    private void insert(int data) {
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
        current.size = size(current.left) + size(current.right) + 1; // 更新節點大小
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

        updateSize(node);
        updateSize(rightChild);
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

        updateSize(node);
        updateSize(leftChild);
    }

    private void updateSize(Node node) {
        node.size = size(node.left) + size(node.right) + 1;
    }

    private int size(Node node) {
        return node == null ? 0 : node.size;
    }

    private void inorderTraversal() {
        inorderRec(root);
    }

    private void inorderRec(Node node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print(node.data + "(" + node.size + ")" + (node.isRed ? "(紅) " : "(黑) "));
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
        System.out.println(" ".repeat(depth * 4) + root.data + "(" + root.size + ")" + (root.isRed ? "(紅)" : "(黑)"));
        printTreeRec(root.left, depth + 1);
    }

    private Node osSelect(Node node, int i) {
        int k = size(node.left) + 1;  // k = rank(node)

        if (i == k) {
            return node;  // ith smallest element
        } else if (i < k) {
            return osSelect(node.left, i);  // search in the left subtree
        } else {
            return osSelect(node.right, i - k);  // search in the right subtree
        }
    }

    private int osRank(Node node, int x) {
        if (node == null) {
            return 0;
        }

        if (x < node.data) {
            return osRank(node.left, x);  // 在左子樹中查詢
        } else if (x > node.data) {
            return size(node.left) + 1 + osRank(node.right, x);  // 在右子樹中查詢
        } else {
            return size(node.left) + 1;  // 找到目標，返回排名
        }
    }
}

class Node {
    int data;
    Node left, right, parent;
    boolean isRed;
    int size;

    Node(int data) {
        this.data = data;
        this.isRed = true; // 剛插入的節點預設紅色
        this.size = 1;      // 葉節點初始大小為 1
    }
}