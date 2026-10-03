class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int t = 0;

        int E =0;
        int So = nums[0];

        int minE = 0;
        int minSo = nums[0];

        for(int n : nums){
            t += n;

           E = Math.max(n, E + n);
            So = Math.max(So, E); 

            minE = Math.min(n, minE + n);
            minSo = Math.min(minSo, minE);
        }
        if (So < 0) {
            return So;
        }
        
        return Math.max(So, t - minSo);
    }
}