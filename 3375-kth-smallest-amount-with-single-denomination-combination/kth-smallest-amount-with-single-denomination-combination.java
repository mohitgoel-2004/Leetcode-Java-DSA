class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long l = 1;
        long minCoin = coins[0];
        for(int c: coins){
            minCoin = Math.min(minCoin, c);
        }
        long r = minCoin * (long) k;

        while(l<r){
            long mid = l + (r-l)/2;
            if(cAmounts(coins, mid) >= k){
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        return l;
    }
    private long cAmounts(int[] coins, long x){
        long cnt = 0;
        int n = coins.length;
        int numSubsets = 1<<n;

        for(int task = 1; task<numSubsets; task++){
            long curr = 1;
            int bit =0;
            boolean overflow = false;

            for(int i=0; i<n;i++){
                if((task & (1<<i)) != 0){
                    bit++;
                    curr = lcm(curr, coins[i]);

                if (curr > x) {
                        overflow = true;
                        break;
                    }
                }
            }

            if (overflow) continue;

            if (bit % 2 == 1) {
                cnt += x / curr;
            } else {
                cnt -= x / curr;
            }
        }

        return cnt;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}