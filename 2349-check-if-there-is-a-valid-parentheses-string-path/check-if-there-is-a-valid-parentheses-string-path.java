class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        int maxLen = (m + n) / 2;
        boolean[][][] visited = new boolean[m][n][maxLen + 1];

        return dfs(grid, 0, 0, 0, visited, m, n, maxLen);
    }

    private boolean dfs(char[][] grid, int r, int c, int balance, boolean[][][] visited, int m, int n, int maxLen) {

        if (grid[r][c] == '(') {
            balance++;
        } else {
            balance--;
        }

        if (balance < 0 || balance > maxLen) {
            return false;
        }

        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }

        if (visited[r][c][balance]) {
            return false;
        }
        visited[r][c][balance] = true;

        if (c + 1 < n && dfs(grid, r, c + 1, balance, visited, m, n, maxLen)) {
            return true;
        }

        if (r + 1 < m && dfs(grid, r + 1, c, balance, visited, m, n, maxLen)) {
            return true;
        }

        return false;
    }
}