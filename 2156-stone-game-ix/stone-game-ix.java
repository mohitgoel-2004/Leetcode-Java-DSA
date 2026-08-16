class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] cnt = new int[3];
        for(int s: stones){
            cnt[s%3]++;
        }
        int c0 = cnt[0], c1=cnt[1], c2=cnt[2];

        if(c0%2==0){
            return Math.min(c1, c2) >=1;
        } else{
            return Math.abs(c1-c2)>2;
        }
    }
}