package algorithm.ls19;

import java.util.*;

public class TopologicalSort {
    public static void main(String[] args) {
        int vertices = 4;
        int[][] edges = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }
        int[] inDegree = new int[vertices];

        for (int[] edge : edges) {
            int from = edge[0], to = edge[1];
            graph.get(from).add(to);
            inDegree[to]++;
        }
        List<Integer> sortedOrder = kahnTopologicalSort(graph, inDegree, vertices);
        System.out.println(sortedOrder);
    }

    private static List<Integer> kahnTopologicalSort(List<List<Integer>> graph, int[] inDegree, int vertices) {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.add(current);

            for (int neighbor : graph.get(current)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        if (result.size() != vertices) {
            throw new IllegalArgumentException("存在環，無法排序！");
        }

        return result;
    }
}
