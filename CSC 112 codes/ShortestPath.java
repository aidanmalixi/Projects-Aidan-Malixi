public class ShortestPath {
    public static int numVertices = 6;

    public static int findMinDistance(int[] distances, boolean[] shortestPathTreeSet) {
        int minDistance = 99999;
        int minIndex = 999;

        for (int vertex = 0; vertex < numVertices; vertex++) {
            if (!shortestPathTreeSet[vertex] && distances[vertex] <= minDistance) {
                minDistance = distances[vertex];
                minIndex = vertex;
            }
        }

        return minIndex;
    }

    public static void printSolution(int[] distances) {
        System.out.println("Distance from Source 0");
        for (int vertex = 1; vertex < numVertices; vertex++) {
            System.out.println(vertex + "\t\t" + distances[vertex]);
        }
    }

    public static void dijkstra(int[][] graph, int source) {
        int[] distances = new int[numVertices];
        boolean[] shortestPathTreeSet = new boolean[numVertices];

        for (int i = 0; i < numVertices; i++) {
            distances[i] = 9999;
            shortestPathTreeSet[i] = false;
        }

        distances[source] = 0;

        for (int count = 0; count < numVertices - 1; count++) {
            int u = findMinDistance(distances, shortestPathTreeSet);
            shortestPathTreeSet[u] = true;

            for (int v = 0; v < numVertices; v++) {
                if (!shortestPathTreeSet[v] && graph[u][v] > 0 && distances[u] < 9999 && (distances[u] + graph[u][v] < distances[v])) {
                    distances[v] = distances[u] + graph[u][v];
                }
            }
        }

        printSolution(distances);
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 3, 15, 4, 0, 0},
                {0, 0, 5, 0, 8, 12},
                {0, 0, 0, 0, 2, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 3, 0, 0, 8},
                {0, 0, 0, 7, 0, 0}
        };

        dijkstra(graph, 0);
    }
}