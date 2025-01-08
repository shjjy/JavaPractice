package algorithm.ls16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KruskalMST {

    public static void main(String[] args) {
        int vertices = 8;
        List<Edge> edges = new ArrayList<>();

        edges.add(new Edge(0, 1, 6));
        edges.add(new Edge(0, 4, 12));
        edges.add(new Edge(1, 4, 5));
        edges.add(new Edge(1, 2, 14));
        edges.add(new Edge(1, 3, 8));
        edges.add(new Edge(2, 3, 3));
        edges.add(new Edge(4, 5, 7));
        edges.add(new Edge(4, 6, 9));
        edges.add(new Edge(3, 5, 10));
        edges.add(new Edge(5, 7, 15));

        List<Edge> mst = kruskalMST(vertices, edges);

        System.out.println("Minimum Spanning Tree:");
        for (Edge edge : mst) {
            System.out.println(edge.src + " -- " + edge.dest + " == " + edge.weight);
        }
    }

    public static List<Edge> kruskalMST(int vertices, List<Edge> edges) {
        List<Edge> mst = new ArrayList<>();
        DisjointSet ds = new DisjointSet(vertices);

        Collections.sort(edges);

        for (Edge edge : edges) {
            int rootSrc = ds.find(edge.src);
            int rootDest = ds.find(edge.dest);

            // 如果不在同一集合中，加入 MST
            if (rootSrc != rootDest) {
                mst.add(edge);
                ds.union(rootSrc, rootDest);
            }

            if (mst.size() == vertices - 1) {
                break;
            }
        }
        return mst;
    }
}

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

class DisjointSet {
    int[] parent, rank;

    public DisjointSet(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    public int find(int node) {
        if (parent[node] != node) {
            parent[node] = find(parent[node]);
        }
        return parent[node];
    }

    public void union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);

        if (root1 != root2) {
            if (rank[root1] > rank[root2]) {
                parent[root2] = root1;
            } else if (rank[root1] < rank[root2]) {
                parent[root1] = root2;
            } else {
                parent[root2] = root1;
                rank[root1]++;
            }
        }
    }
}
