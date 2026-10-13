class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] sq = new long[n];

        for(int i=0; i<n; i++){
            long val = (long) nums[i];
            sq[i] = val * val;
        }
        Arrays.sort(sq);

        long maxS = 0;

        for(int i=0; i<n/2; i++){
            maxS -= sq[i];
        }
        for(int i=n/2; i<n; i++){
            maxS += sq[i];
        }

        return maxS;
    }
}