class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(new int[] { edge[1], edge[2] });
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        int[][] dist = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        int initialCount = 1;
        pq.offer(new int[] { 0, 0, initialCount });
        dist[0][initialCount] = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int weight = curr[0];
            int u = curr[1];
            int count = curr[2];

            if (u == n - 1) {
                return weight;
            }

            if (weight > dist[u][count]) {
                continue;
            }

            for (int[] neighbor : graph[u]) {
                int v = neighbor[0];
                int w = neighbor[1];

                int nextCount;
                if (labels.charAt(u) == labels.charAt(v)) {
                    nextCount = count + 1;
                } else {
                    nextCount = 1;
                }

            if (nextCount <= k && weight + w < dist[v][nextCount]) {
                    dist[v][nextCount] = weight + w;
                    pq.offer(new int[] { weight + w, v, nextCount });
                }
            }
        }

        int minWeight = Integer.MAX_VALUE;
        for (int c = 1; c <= k; c++) {
            minWeight = Math.min(minWeight, dist[n - 1][c]);
        }

        return minWeight == Integer.MAX_VALUE ? -1 : minWeight;
    }
}