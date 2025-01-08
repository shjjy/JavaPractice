package algorithm.ls18;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BellmanFord {

    public static void main(String[] args) {
        Map<String, List<BellmanFordEdge>> graph = new HashMap<>();
        graph.put("A", Arrays.asList(new BellmanFordEdge("B", -1),
                new BellmanFordEdge("C", 4)));
        graph.put("B", Arrays.asList(new BellmanFordEdge("C", 3),new BellmanFordEdge("E", 2),
                new BellmanFordEdge("D", 2)));
        graph.put("C", List.of());
        graph.put("D", Arrays.asList(new BellmanFordEdge("B", 1),
                new BellmanFordEdge("C", 5)));
        graph.put("E", List.of(new BellmanFordEdge("D", -3)));

        bellmanFord(graph, "A");
    }

    private static void bellmanFord(Map<String, List<BellmanFordEdge>> graph, String start) {
        Map<String, Integer> dist = new HashMap<>();
        Map<String, String> previous = new HashMap<>();

        for (String node : graph.keySet()) {
            dist.put(node, Integer.MAX_VALUE);
            previous.put(node, null);
        }
        dist.put(start, 0);

        for (int i = 0; i < graph.size() - 1; i++) {
            for (String node : graph.keySet()) {
                for (BellmanFordEdge edge : graph.get(node)) {
                    if (dist.get(node) != Integer.MAX_VALUE && dist.get(node) + edge.weight < dist.get(edge.target)) {
                        dist.put(edge.target, dist.get(node) + edge.weight);
                        previous.put(edge.target, node);
                    }
                }
            }
        }

        for (String node : graph.keySet()) {
            for (BellmanFordEdge edge : graph.get(node)) {
                if (dist.get(node) != Integer.MAX_VALUE && dist.get(node) + edge.weight < dist.get(edge.target)) {
                    System.out.println("存在負環！");
                    return;
                }
            }
        }

        System.out.println("最短路徑:");
        for (String node : dist.keySet()) {
            if (dist.get(node) == Integer.MAX_VALUE) {
                System.out.println("從 " + start + " 到 " + node + " 無法到達");
            } else {
                System.out.print("從 " + start + " 到 " + node + " 的最短距離是: " + dist.get(node));
                System.out.print(", 路徑: ");
                printPath(previous, node);  // 打印最短路徑
                System.out.println();
            }
        }
    }

    private static void printPath(Map<String, String> previous, String node) {
        if (node == null) {
            return;
        }
        printPath(previous, previous.get(node));
        System.out.print(node + " ");
    }
}

class BellmanFordEdge {
    String target;
    int weight;

    BellmanFordEdge(String target, int weight) {
        this.target = target;
        this.weight = weight;
    }
}
