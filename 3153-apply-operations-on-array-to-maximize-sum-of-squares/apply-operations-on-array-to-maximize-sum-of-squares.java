class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCount = new int[32];
        
        // Step 1: Count the total frequency of set bits at each position
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                if ((num & (1 << i)) != 0) {
                    bitCount[i]++;
                }
            }
        }
        
        long mod = 1_000_000_007;
        long totalSum = 0;
        
        // Step 2: Greedily form the k largest possible numbers
        for (int i = 0; i < k; i++) {
            long currentNum = 0;
            for (int bit = 0; bit < 32; bit++) {
                if (bitCount[bit] > 0) {
                    currentNum |= (1L << bit);
                    bitCount[bit]--;
                }
            }
            // Step 3: Add the square of the constructed number modulo 10^9 + 7
            totalSum = (totalSum + (currentNum * currentNum) % mod) % mod;
        }
        
        return (int) totalSum;
    }
}