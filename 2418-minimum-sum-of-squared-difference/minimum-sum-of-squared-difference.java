class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long t = (long) k1 + k2;

        int maxDiff = 0;
        int[] diffCnt = new int[100005];
        for(int i=0; i<n; i++){
            int  diff = Math.abs(nums1[i] - nums2[i]);
            diffCnt[diff]++;
            if(diff > maxDiff){
                maxDiff = diff;
            }
        }

        for(int d = maxDiff; d>0 && t>0; d--){
            if(diffCnt[d] == 0) continue;

            long take = Math.min(t, diffCnt[d]);
            diffCnt[d] -= take;
            diffCnt[d - 1] += take;
            t -= take; 
        }

        long minSum = 0;
        for(int d = 1; d <= maxDiff; d++){
            if(diffCnt[d] > 0){
                minSum += (long) diffCnt[d] * d * d;
            }
        }
        return minSum;
    }
}