package algorithm.ls11;

public class RedBlackIntervalTree {

    public static void main(String[] args) {
        RedBlackIntervalTree tree = new RedBlackIntervalTree();

        // 插入區間
        tree.insert(new RedBlackInterval(4, 8));
        tree.insert(new RedBlackInterval(5, 11));
        tree.insert(new RedBlackInterval(7, 10));
        tree.insert(new RedBlackInterval(15, 18));
        tree.insert(new RedBlackInterval(17, 19));
        tree.insert(new RedBlackInterval(22, 23));

        System.out.println("從小到大的排序:");
        tree.inorderTraversal();
        System.out.println("\nRed Black Tree:");
        tree.printTree();

        RedBlackInterval query1 = new RedBlackInterval(12, 14);
        System.out.println("\n查詢區間 " + query1 + " 是否重疊：" + tree.overlapSearch(query1));

        RedBlackInterval query2 = new RedBlackInterval(21, 30);
        System.out.println("查詢區間 " + query2 + " 是否重疊：" + tree.overlapSearch(query2));

        RedBlackInterval query3 = new RedBlackInterval(14, 16);
        System.out.println("查詢區間 " + query3 + " 是否重疊：" + tree.overlapSearch(query3));
    }

    private RedBlackIntervalNode root;

    // 插入區間
    public void insert(RedBlackInterval interval) {
        RedBlackIntervalNode newNode = new RedBlackIntervalNode(interval);
        root = bstInsert(root, null, newNode);
        fixViolation(newNode);
    }

    private RedBlackIntervalNode bstInsert(RedBlackIntervalNode current, RedBlackIntervalNode parent, RedBlackIntervalNode newNode) {
        if (current == null) {
            newNode.parent = parent;
            return newNode;
        }

        if (newNode.interval.start < current.interval.start) {
            current.left = bstInsert(current.left, current, newNode);
        } else {
            current.right = bstInsert(current.right, current, newNode);
        }

        current.updateMaxEnd();
        return current;
    }

    private void fixViolation(RedBlackIntervalNode node) {
        while (node != root && node.parent.isRed) {
            RedBlackIntervalNode parent = node.parent;
            RedBlackIntervalNode grandparent = parent.parent;

            if (parent == grandparent.left) {
                RedBlackIntervalNode uncle = grandparent.right;

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
                RedBlackIntervalNode uncle = grandparent.left;

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
        root.isRed = false; // 根節點必須是黑色
    }

    private void leftRotate(RedBlackIntervalNode node) {
        RedBlackIntervalNode rightChild = node.right;
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

        node.updateMaxEnd();
        rightChild.updateMaxEnd();
    }

    private void rightRotate(RedBlackIntervalNode node) {
        RedBlackIntervalNode leftChild = node.left;
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

        node.updateMaxEnd();
        leftChild.updateMaxEnd();
    }

    // 區間查詢
    public boolean overlapSearch(RedBlackInterval query) {
        return overlapSearchRec(root, query);
    }

    private boolean overlapSearchRec(RedBlackIntervalNode node, RedBlackInterval query) {
        if (node == null) {
            return false;
        }

        if (node.interval.overlaps(query)) {
            return true;
        }

        if (node.left != null && node.left.maxEnd >= query.start) {
            return overlapSearchRec(node.left, query);
        }

        return overlapSearchRec(node.right, query);
    }

    // 中序遍歷
    public void inorderTraversal() {
        inorderRec(root);
    }

    private void inorderRec(RedBlackIntervalNode node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.println(node.interval + " (maxEnd: " + node.maxEnd + ") " + (node.isRed ? "(紅)" : "(黑)"));
            inorderRec(node.right);
        }
    }

    public void printTree() {
        printTreeRec(root, 0);
    }

    private void printTreeRec(RedBlackIntervalNode node, int depth) {
        if (node == null) {
            System.out.println(" ".repeat(depth * 4) + "null(黑)");
            return;
        }

        // 先打印右子樹
        printTreeRec(node.right, depth + 1);

        // 打印當前節點
        String color = node.isRed ? "(紅)" : "(黑)";
        System.out.println(" ".repeat(depth * 4) + "[" + node.interval.start + ", " + node.interval.end + "]"
                + " maxEnd: " + node.maxEnd  + " " + color);

        // 再打印左子樹
        printTreeRec(node.left, depth + 1);
    }
}

// 區間類
class RedBlackInterval {
    int start, end;

    RedBlackInterval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public boolean overlaps(RedBlackInterval other) {
        return this.start < other.end && other.start < this.end;
    }

    @Override
    public String toString() {
        return "[" + start + ", " + end + "]";
    }
}

// 節點類
class RedBlackIntervalNode {
    RedBlackInterval interval;
    int maxEnd;
    RedBlackIntervalNode left, right, parent;
    boolean isRed;

    RedBlackIntervalNode(RedBlackInterval interval) {
        this.interval = interval;
        this.maxEnd = interval.end;
        this.isRed = true; // 新插入的節點為紅色
    }

    public void updateMaxEnd() {
        this.maxEnd = Math.max(interval.end,
                Math.max(left != null ? left.maxEnd : Integer.MIN_VALUE,
                        right != null ? right.maxEnd : Integer.MIN_VALUE));
    }
}
