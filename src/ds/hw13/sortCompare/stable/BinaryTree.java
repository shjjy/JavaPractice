package ds.hw13.sortCompare.stable;

import ds.hw13.sortCompare.Sorter;

import java.util.Arrays;

//建立 BST並寫入資料
//透過中序解析 Tree 並寫入 Array

public class BinaryTree implements Sorter {
    @Override
    public void sort(int[] data) {
        Node node = null;
        for (int i : data) {
            node = visit(node, i);
        }
        int[] nodeArray = new int[data.length];
        int[] indexArray = new int[1];
        storeToArray(node, nodeArray, indexArray);
        data = nodeArray;
    }

    private Node visit(Node node, int data) {
        if (node == null) return new Node(data);
        if (node.data > data) {
            node.left = visit(node.left, data);
        }else if (node.data < data) {
            node.right = visit(node.right, data);
        }
        return node;
    }

    private void storeToArray(Node node, int[] nodeArray, int[] indexArray) {
        if (node == null) return;
        storeToArray(node.left, nodeArray, indexArray);
        nodeArray[indexArray[0]] = node.data;
        indexArray[0]++;
        storeToArray(node.right, nodeArray, indexArray);
    }

    @Override
    public String getName() {
        return "Binary Tree Sort";
    }
}

class Node {
    int data;
    Node left, right;

    public Node(int data) {
        this.data = data;
    }
}
