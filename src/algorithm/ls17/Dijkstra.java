package algorithm.ls17;

import java.util.*;

public class Dijkstra {

    public static void main(String[] args) {
        Map<String, List<DijkstraEdge>> graph = new HashMap<>();
        graph.put("A", Arrays.asList(new DijkstraEdge("B", 10, ""),
                new DijkstraEdge("C", 3, "")));
        graph.put("B", Arrays.asList(new DijkstraEdge("C", 1, ""),
                new DijkstraEdge("D", 2, "")));
        graph.put("C", Arrays.asList(new DijkstraEdge("B", 4, ""),
                new DijkstraEdge("D", 8, ""),
                new DijkstraEdge("E", 2, "")));
        graph.put("D", List.of(new DijkstraEdge("E", 7, "")));
        graph.put("E", List.of(new DijkstraEdge("D", 9, "")));
//        graph.put("F", Collections.emptyList());

        dijkstra(graph, "A");
    }

    private static void dijkstra(Map<String, List<DijkstraEdge>> graph, String start) {
        Map<String, DijkstraEdge> shortestMap = new HashMap<>();

        for (String node : graph.keySet()) {
            shortestMap.put(node, new DijkstraEdge(node, Integer.MAX_VALUE, null));
        }

        shortestMap.put(start, new DijkstraEdge(start, 0, null));

        PriorityQueue<DijkstraEdge> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
        pq.add(shortestMap.get(start));

        Set<String> visited = new HashSet<>();

        while (!pq.isEmpty()) {
            DijkstraEdge currentEdge = pq.poll();
            String current = currentEdge.target;

            if (visited.contains(current)) {
                continue;
            }

            visited.add(current);

            for (DijkstraEdge edge : graph.get(current)) {
                if (visited.contains(edge.target)) {
                    continue;
                }

                int newDistance = currentEdge.weight + edge.weight;

                if (newDistance < shortestMap.get(edge.target).weight) {
                    shortestMap.put(edge.target, new DijkstraEdge(edge.target, newDistance, current));
                    pq.add(new DijkstraEdge(edge.target, newDistance, current));
                }
            }
        }

        for (String node : shortestMap.keySet()) {
            System.out.print("從 " + start + " 到 " + node + " 最短距離: " + shortestMap.get(node).weight);
            System.out.print(", 路徑: ");
            printPath(shortestMap, node);
            System.out.println();
        }
    }

    private static void printPath(Map<String, DijkstraEdge> nodeInfoMap, String node) {
        if (node == null) {
            return;
        }
        printPath(nodeInfoMap, nodeInfoMap.get(node).previous); // 遞迴回溯前一個節點
        System.out.print(node + " "); // 打印當前節點
    }
}

class DijkstraEdge {
    String target;
    int weight;
    String previous;

    DijkstraEdge(String target, int distance, String previous) {
        this.target = target;
        this.weight = distance;
        this.previous = previous;
    }
}
