class Solution {
    public String[] createGrid(int m, int n) {
        char[][] temp = new char[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j] = '#';
            }
        }

        for (int j = 0; j < n; j++) {
            temp[0][j] = '.';
        }

        for (int i = 0; i < m; i++) {
            temp[i][n - 1] = '.';
        }
        
        String[] grid = new String[m];
        for (int i = 0; i < m; i++) {
            grid[i] = new String(temp[i]);
        }
        
        return grid;
    }
}