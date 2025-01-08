package algorithm.ls16;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PrimMST {

    public static void main(String[] args) {
        int vertices = 8;
        List<List<PrimEdge>> graph = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }

        addEdge(graph, 0, 1, 6);
        addEdge(graph, 0, 4, 12);
        addEdge(graph, 1, 4, 5);
        addEdge(graph, 1, 2, 14);
        addEdge(graph, 1, 3, 8);
        addEdge(graph, 2, 3, 3);
        addEdge(graph, 4, 5, 7);
        addEdge(graph, 4, 6, 9);
        addEdge(graph, 3, 5, 10);
        addEdge(graph, 5, 7, 15);

        List<int[]> mst = primMST(graph, vertices);

        System.out.println("Minimum Spanning Tree:");
        for (int[] edge : mst) {
            System.out.println(edge[0] + " -- " + edge[1] + " == " + edge[2]);
        }
    }

    private static void addEdge(List<List<PrimEdge>> graph, int u, int v, int weight) {
        graph.get(u).add(new PrimEdge(v, weight));
        graph.get(v).add(new PrimEdge(u, weight)); // 無向圖，雙向
    }

    public static List<int[]> primMST(List<List<PrimEdge>> graph, int vertices) {
        boolean[] visited = new boolean[vertices];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e[2]));
        List<int[]> mst = new ArrayList<>();

        visited[0] = true;
        for (PrimEdge edge : graph.get(0)) {
            pq.offer(new int[]{0, edge.to, edge.weight});
        }

        while (!pq.isEmpty() && mst.size() < vertices - 1) {
            int[] edge = pq.poll();
            int u = edge[0], v = edge[1], weight = edge[2];

            if (visited[v]) continue;

            mst.add(new int[]{u, v, weight});
            visited[v] = true;

            for (PrimEdge nextEdge : graph.get(v)) {
                if (!visited[nextEdge.to]) {//節點被訪問過就不再訪問
                    pq.offer(new int[]{v, nextEdge.to, nextEdge.weight});
                }
            }
        }

        return mst;
    }
}

class PrimEdge {
    int to, weight;

    public PrimEdge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}
