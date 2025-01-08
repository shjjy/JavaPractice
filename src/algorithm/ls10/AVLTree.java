package algorithm.ls10;

public class AVLTree {

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);

        System.out.println("從小到大的排序:");
        tree.inorderTraversal(); // 印出中序遍歷以檢視 AVL 樹結構
        System.out.println("\nAVL Tree:");
        tree.printTree();

        System.out.println("\nSearch 4: " + tree.search(4)); // 檢查是否存在
        System.out.println("Search 7: " + tree.search(7)); // 檢查不存在的數值
    }

    private Node root;

    private static class Node {
        int key, height;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.height = 1; // 新節點的高度默認為 1
        }
    }

    // 插入節點
    public void insert(int key) {
        root = insert(root, key);
    }

    private Node insert(Node node, int key) {
        // 樹空，插入新的節點
        if (node == null) {
            return new Node(key);
        }

        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            // 重複的鍵，不進行插入
            return node;
        }

        // 更新節點的高度
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // 計算平衡因子並進行適當的旋轉
        int balance = getBalance(node);

        // 左右平衡情況
        if (balance > 1 && key < node.left.key) {
            return rightRotate(node); // 右旋轉
        }
        if (balance < -1 && key > node.right.key) {
            return leftRotate(node); // 左旋轉
        }
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left); // 左右旋轉
            return rightRotate(node);
        }
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right); // 右左旋轉
            return leftRotate(node);
        }

        return node;
    }

    // 計算節點的高度
    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    // 計算平衡因子
    private int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    // 右旋轉
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // 旋轉
        x.right = y;
        y.left = T2;

        // 更新高度
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // 左旋轉
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // 旋轉
        y.left = x;
        x.right = T2;

        // 更新高度
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // 中序遍歷以檢視 AVL 樹結構
    public void inorderTraversal() {
        inorderTraversal(root);
    }

    private void inorderTraversal(Node node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.print(node.key + " ");
            inorderTraversal(node.right);
        }
    }

    // 查找節點
    public boolean search(int key) {
        return search(root, key) != null;
    }

    private Node search(Node node, int key) {
        if (node == null || node.key == key) {
            return node;
        }

        if (key < node.key) {
            return search(node.left, key);
        }

        return search(node.right, key);
    }

    public void printTree() {
        printTreeRec(root, 0);
    }

    private void printTreeRec(Node root, int depth) {
        if (root == null) {
            System.out.println(" ".repeat(depth * 4) + "null");
            return;
        }
        printTreeRec(root.right, depth + 1);
        System.out.println(" ".repeat(depth * 4) + root.key);
        printTreeRec(root.left, depth + 1);
    }
}
