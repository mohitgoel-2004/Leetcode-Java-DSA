class Solution {
    // Helper class to store neighbor and the edge weight
    private static class Node {
        String key;
        double val;
        Node(String key, double val) {
            this.key = key;
            this.val = val;
        }
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // Step 1: Build the graph
        Map<String, List<Node>> graph = new HashMap<>();
        
        for (int i = 0; i < equations.size(); i++) {
            String u = equations.get(i).get(0);
            String v = equations.get(i).get(1);
            double val = values[i];
            
            graph.putIfAbsent(u, new ArrayList<>());
            graph.putIfAbsent(v, new ArrayList<>());
            
            graph.get(u).add(new Node(v, val));
            graph.get(v).add(new Node(u, 1.0 / val));
        }
        
        // Step 2: Evaluate each query using DFS
        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String src = queries.get(i).get(0);
            String dest = queries.get(i).get(1);
            
            if (!graph.containsKey(src) || !graph.containsKey(dest)) {
                results[i] = -1.0;
            } else if (src.equals(dest)) {
                results[i] = 1.0;
            } else {
                Set<String> visited = new HashSet<>();
                results[i] = dfs(src, dest, 1.0, graph, visited);
            }
        }
        
        return results;
    }
    
    private double dfs(String current, String target, double currentProduct, Map<String, List<Node>> graph, Set<String> visited) {
        if (current.equals(target)) {
            return currentProduct;
        }
        
        visited.add(current);
        
        for (Node neighbor : graph.get(current)) {
            if (!visited.contains(neighbor.key)) {
                double result = dfs(neighbor.key, target, currentProduct * neighbor.val, graph, visited);
                if (result != -1.0) {
                    return result;
                }
            }
        }
        
        return -1.0;
    }
}