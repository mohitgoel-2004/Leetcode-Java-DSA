class Solution {
    public long countCommas(long n) {
        long t =0;

        for(long s = 1000; s<=n; s *=1000){
            t += (n-s+1);
            if(s > Long.MAX_VALUE / 1000){
                break;
            }
        }
        return t;
    }
}