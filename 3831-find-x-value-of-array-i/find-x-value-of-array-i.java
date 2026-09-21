class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] res = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {
            long[] nextDp = new long[k];
            int val = num % k;

            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newRem = (r * val) % k;
                    nextDp[newRem] += dp[r];
                }
            }

            nextDp[val]++;

            for (int r = 0; r < k; r++) {
                res[r] += nextDp[r];
            }

            dp = nextDp;
        }

        return res;
    }
}