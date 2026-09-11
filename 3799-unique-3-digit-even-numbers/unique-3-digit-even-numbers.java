class Solution {
    public int totalNumbers(int[] digits) {
        int[] cnt = new int[10];
        for(int d : digits){
            cnt[d]++;
        }
        int t=0;

        for (int n = 100; n<=998; n+=2){
            int d1 = n/100;
            int d2 = (n/10) % 10;
            int d3 = n%10;

            int[] currCnt = new int[10];
            currCnt[d1]++;
            currCnt[d2]++;
            currCnt[d3]++;

            boolean isValid = true;
            for (int i=0; i<10; i++){
                if(currCnt[i] > cnt[i]){
                    isValid = false;
                    break;
                }
            }
            if(isValid){
                t++;
            }
        }
        return t;
    }
}