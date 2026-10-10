class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }

        int r = matrix.length;
        int c = matrix[0].length;
        int maxL = 0;

        int[][] dp = new int[r + 1][c + 1];

        for(int i = 1; i<=r; i++){
            for(int j = 1; j<= c; j++){
                if(matrix[i-1][j-1] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    maxL = Math.max(maxL, dp[i][j]);
                }
        }
        }
     return maxL * maxL;
    }
}