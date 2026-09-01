import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int startR = -1, startC = -1;
        
        List<int[]> litters = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);
                if (ch == 'S') {
                    startR = i;
                    startC = j;
                } else if (ch == 'L') {
                    litters.add(new int[]{i, j});
                }
            }
        }
        
        int numLitter = litters.size();
        if (numLitter == 0) return 0;
        
        int targetMask = (1 << numLitter) - 1;
        
        int[][][] maxEnergy = new int[m][n][1 << numLitter];
        for (int[][] mat : maxEnergy) {
            for (int[] arr : mat) {
                Arrays.fill(arr, -1);
            }
        }
        
        Queue<int[]> queue = new LinkedList<>(); 
        
        int startMask = 0;
        for (int i = 0; i < numLitter; i++) {
            if (litters.get(i)[0] == startR && litters.get(i)[1] == startC) {
                startMask |= (1 << i);
            }
        }
        
        queue.offer(new int[]{startR, startC, startMask, energy});
        maxEnergy[startR][startC][startMask] = energy;
        
        int steps = 0;
        int[][] dirs = {{ -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }};
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] curr = queue.poll();
                int r = curr[0], c = curr[1], mask = curr[2], e = curr[3];
                
                if (mask == targetMask) return steps;
                if (e == 0) continue;
                
                for (int[] d : dirs) {
                    int nr = r + d[0];
                    int nc = c + d[1];
                    
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && classroom[nr].charAt(nc) != 'X') {
                        char cell = classroom[nr].charAt(nc);
                        int nextE = e - 1;
                        int nextMask = mask;
                        
                        if (cell == 'R') {
                            nextE = energy;
                        } else if (cell == 'L') {
                            for (int i = 0; i < numLitter; i++) {
                                if (litters.get(i)[0] == nr && litters.get(i)[1] == nc) {
                                    nextMask |= (1 << i);
                                    break;
                                }
                            }
                        }
                        
                        if (nextE > maxEnergy[nr][nc][nextMask]) {
                            maxEnergy[nr][nc][nextMask] = nextE;
                            queue.offer(new int[]{nr, nc, nextMask, nextE});
                        }
                    }
                }
            }
            steps++;
        }
        
        return -1;
    }
}