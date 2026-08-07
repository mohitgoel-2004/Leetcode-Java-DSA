class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n= graph.length;
        List<List<Integer>> adj = new ArrayList<>();
        
        for(int i = 0; i < n; i++)
            adj.add(new ArrayList<>());
        
        int[] indegree = new int[n];
        
        for(int i = 0; i < n; i++){
            for(int ele: graph[i]){
                adj.get(ele).add(i);
                indegree[i]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        
        for(int i = 0; i < n; i++){
            if(indegree[i] == 0)
                q.add(i);
        }
        
        List<Integer> safeNodes = new ArrayList<>();
        
        while(!q.isEmpty()){
            int front = q.poll();
            safeNodes.add(front);
            
            for(int ele : adj.get(front)){
                indegree[ele]--;
                if(indegree[ele] == 0){
                    q.add(ele);
                }
            }
        }
        Collections.sort(safeNodes);
        return safeNodes;
    }
}