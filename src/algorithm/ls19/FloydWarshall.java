package algorithm.ls19;

public class FloydWarshall {

    public static void main(String[] args) {

        int[][] graph = {
                {0, 2, 6, 9},
                {0, 0, 3, 0},
                {7, 0, 0, 1},
                {5, 0, 12, 0}
        };
        floydWarshall(graph);

        int[][] graph2 = {
                {0, 2, 0, 0},
                {0, 0, 3, 0},
                {0, 0, 0, 4},
                {0, 0, 0, 0}
        };
        floydWarshall(graph2);
    }

    static final int INF = 99999;

    public static void floydWarshall(int[][] graph) {
        int V = graph.length;

        int[][] dist = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else if (graph[i][j] != 0) {
                    dist[i][j] = graph[i][j];
                } else {
                    dist[i][j] = INF;
                }
            }
        }
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        print(dist);
    }

    public static void print(int[][] dist) {
        int V = dist.length;
        System.out.println("最短距離矩陣：");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == INF) {
                    System.out.print("NIL ");
                } else {
                    System.out.printf("%3d ", dist[i][j]);
                }
            }
            System.out.println();
        }
    }
}