class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;

        int[][][] go = new int[m][n][2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int r = i, c = j, dir = 0; 
                boolean valid = true;
                while (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == 1) {
                    if (dir == 0) {
                        r++; 
                        dir = 1;
                    } else {
                        c++;
                        dir = 0;
                    }
                }
                if (r >= 0 && r < m && c >= 0 && c < n) {
                    go[i][j][0] = r * n + c; 
                } else {
                    go[i][j][0] = -1; 
                }

                r = i; c = j; dir = 1; 
                while (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == 1) {
                    if (dir == 0) {
                        r++;
                        dir = 1;
                    } else {
                        c++;
                        dir = 0;
                    }
                }
                if (r >= 0 && r < m && c >= 0 && c < n) {
                    go[i][j][1] = r * n + c;
                } else {
                    go[i][j][1] = -1;
                }
            }
        }

        long[][] dp = new long[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0) continue;

                if (j + 1 < n) {
                    int nextR, nextC;
                    if (grid[i][j + 1] == 1) {
                        int target = go[i][j + 1][0];
                        if (target != -1) {
                            nextR = target / n;
                            nextC = target % n;
                            dp[nextR][nextC] = (dp[nextR][nextC] + dp[i][j]) % MOD;
                        }
                    } else {
                        dp[i][j + 1] = (dp[i][j + 1] + dp[i][j]) % MOD;
                    }
                }

                if (i + 1 < m) {
                    if (grid[i + 1][j] == 1) {
                        int target = go[i + 1][j][1];
                        if (target != -1) {
                            int nextR = target / n;
                            int nextC = target % n;
                            dp[nextR][nextC] = (dp[nextR][nextC] + dp[i][j]) % MOD;
                        }
                    } else {
                        dp[i + 1][j] = (dp[i + 1][j] + dp[i][j]) % MOD;
                    }
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }
}