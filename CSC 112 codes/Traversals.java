import java.util.*;

public class Traversals {
    private Map<String, List<String>> graph;

    public Traversals(Map<String, List<String>> graph) {
        this.graph = graph;
    }

    public List<String> dfs(String start) {
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();
        List<String> traversalOrder = new ArrayList<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            String n = stack.pop();

            if (!visited.contains(n)) {
                visited.add(n);
                traversalOrder.add(n);

                List<String> neighbors = graph.getOrDefault(n, new ArrayList<>());
                Collections.reverse(neighbors);

                for (String neighbor : neighbors) {
                    stack.push(neighbor);
                }
            }
        }

        return traversalOrder;
    }

    public List<String> bfs(String start) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        List<String> traversalOrder = new ArrayList<>();

        queue.add(start);

        while (!queue.isEmpty()) {
            String n = queue.poll();

            if (!visited.contains(n)) {
                visited.add(n);
                traversalOrder.add(n);

                List<String> neighbors = graph.getOrDefault(n, new ArrayList<>());

                for (String neighbor : neighbors) {
                    queue.add(neighbor);
                }
            }
        }

        return traversalOrder;
    }

    public static void main(String[] args) {
       
        Map<String, List<String>> australiaDigraph = new HashMap<>();
        australiaDigraph.put("Sydney", Arrays.asList("Canberra"));
        australiaDigraph.put("Canberra", Arrays.asList("Melbourne", "Brisbane"));
        australiaDigraph.put("Melbourne", Arrays.asList("Adelaide"));
        australiaDigraph.put("Adelaide", Arrays.asList("Perth"));
        australiaDigraph.put("Perth", Arrays.asList("Darwin"));
        australiaDigraph.put("Darwin", new ArrayList<>());
        australiaDigraph.put("Brisbane", Arrays.asList("Cairns"));
        australiaDigraph.put("Cairns", new ArrayList<>());

        Traversals graphTraversal = new Traversals(australiaDigraph);

        List<String> dfsOrder = graphTraversal.dfs("Sydney");
        System.out.println("DFS Order: " + dfsOrder);

        List<String> bfsOrder = graphTraversal.bfs("Sydney");
        System.out.println("BFS Order: " + bfsOrder);
    }
}