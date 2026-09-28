class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] buckets = new int[n +1];

        for(int c : citations){
            if(c>=n){
                buckets[n]++;
            }else{
                buckets[c]++;
            }
        }
        int cnt =0;
        for(int i =n; i>=0; i--){
            cnt += buckets[i];
            if(cnt >=i){
                return i;
            }
        }
        return 0;
    }
}