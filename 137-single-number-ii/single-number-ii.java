class Solution {
    public int singleNumber(int[] nums) {
        int ones =0 , twos =0;
        for(int n: nums){
            twos |= (ones & n);
            ones ^= n;

            int common = ~(ones & twos);
            ones &= common;
            twos &= common;
        }

        return ones;
    }
}