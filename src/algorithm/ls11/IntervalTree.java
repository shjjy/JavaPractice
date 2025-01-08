package algorithm.ls11;

public class IntervalTree {

    public static void main(String[] args) {
        IntervalTree tree = new IntervalTree();

        tree.insert(new Interval(4, 8));
        tree.insert(new Interval(5, 11));
        tree.insert(new Interval(7, 10));
        tree.insert(new Interval(15, 18));
        tree.insert(new Interval(17, 19));
        tree.insert(new Interval(22, 23));

        System.out.println(tree.overlapSearch(new Interval(12, 14)));
        System.out.println(tree.overlapSearch(new Interval(21, 30)));
    }

    IntervalNode root;

    

    private void insert(Interval interval) {
        root = insertRec(root, interval);
    }

    private IntervalNode insertRec(IntervalNode node, Interval interval) {
        if (node == null) {
            return new IntervalNode(interval);
        }

        if (interval.start < node.interval.start) {
            node.left = insertRec(node.left, interval);
        } else {
            node.right = insertRec(node.right, interval);
        }

        node.maxEnd = Math.max(node.maxEnd, interval.end);

        return node;
    }

    private boolean overlapSearch(Interval interval) {
        return overlapSearchRec(root, interval);
    }

    private boolean overlapSearchRec(IntervalNode node, Interval interval) {
        if (node == null) {
            return false;
        }

        if (doOverlap(node.interval, interval)) {
            return true;
        }

        if (node.left != null && node.left.maxEnd >= interval.start) {
            return overlapSearchRec(node.left, interval);
        }

        return overlapSearchRec(node.right, interval);
    }

    private boolean doOverlap(Interval i1, Interval i2) {
        return i1.start < i2.end && i2.start < i1.end;
    }
}

class IntervalNode {
    Interval interval;
    int maxEnd; // Maximum end point in the subtree
    IntervalNode left, right;

    IntervalNode(Interval interval) {
        this.interval = interval;
        this.maxEnd = interval.end;
    }
}

class Interval {
    int start, end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}