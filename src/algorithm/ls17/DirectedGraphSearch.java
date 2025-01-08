package algorithm.ls17;

import java.util.*;

public class DirectedGraphSearch {
    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", Arrays.asList("B", "D"));
        graph.put("B", Arrays.asList("C", "E"));
        graph.put("C", List.of());
        graph.put("D", Arrays.asList("B", "E"));
        graph.put("E", Arrays.asList("C", "G", "I"));
        graph.put("F", Arrays.asList("D", "H"));
        graph.put("G", Arrays.asList("I", "F"));
        graph.put("H", List.of("G"));
        graph.put("I", List.of("H"));

        System.out.println("BFS:");
        bfs(graph, "A");

        System.out.println("\nDFS:");
        dfs(graph, "A");
    }

    public static void bfs(Map<String, List<String>> graph, String start) {
        Map<String, Integer> depthMap = new HashMap<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(start);
        depthMap.put(start, 0);
        queue.offer(start);

        while (!queue.isEmpty()) {
            String currentNode = queue.poll();
            int currentDepth = depthMap.get(currentNode);

            System.out.println("節點: " + currentNode + "，深度: " + currentDepth);

            for (String neighbor : graph.get(currentNode)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    depthMap.put(neighbor, currentDepth + 1);
                    queue.offer(neighbor);
                }
            }
        }
    }

    public static void dfs(Map<String, List<String>> graph, String start) {
        Map<String, Integer> depthMap = new HashMap<>();
        Set<String> visited = new HashSet<>();

        dfsRecursive(graph, start, 0, visited, depthMap);

        for (String node : depthMap.keySet()) {
            System.out.println("節點: " + node + "，深度: " + depthMap.get(node));
        }
    }

    private static void dfsRecursive(Map<String, List<String>> graph, String currentNode, int depth,
                                     Set<String> visited, Map<String, Integer> depthMap) {
        visited.add(currentNode);
        depthMap.put(currentNode, depth);

        for (String neighbor : graph.get(currentNode)) {
            if (!visited.contains(neighbor)) {
                dfsRecursive(graph, neighbor, depth + 1, visited, depthMap);
            }
        }
    }
}
