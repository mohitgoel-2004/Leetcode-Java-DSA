class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int cnt = 0;
        boolean[][] vis = new boolean[m][n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1' && !vis[i][j]){
                    bfs(i, j, grid, vis);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    class Pair{
        int x, y;
        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    private void bfs(int i, int j, char[][] grid, boolean[][] vis){
        int m = grid.length, n = grid[0].length;
        Queue<Pair> q = new LinkedList<>();

        q.add(new Pair(i, j));
        vis[i][j] = true; // ✅ FIX

        while(!q.isEmpty()){
            Pair front = q.remove();
            int x = front.x, y = front.y;

            // top
            if(x > 0 && !vis[x-1][y] && grid[x-1][y] == '1'){
                q.add(new Pair(x-1, y));
                vis[x-1][y] = true;
            }

            // bottom
            if(x + 1 < m && !vis[x+1][y] && grid[x+1][y] == '1'){ // ✅ FIX
                q.add(new Pair(x+1, y));
                vis[x+1][y] = true;
            }

            // left
            if(y > 0 && !vis[x][y-1] && grid[x][y-1] == '1'){
                q.add(new Pair(x, y-1));
                vis[x][y-1] = true;
            }

            // right
            if(y + 1 < n && !vis[x][y+1] && grid[x][y+1] == '1'){
                q.add(new Pair(x, y+1));
                vis[x][y+1] = true;
            }
        }
    }
}