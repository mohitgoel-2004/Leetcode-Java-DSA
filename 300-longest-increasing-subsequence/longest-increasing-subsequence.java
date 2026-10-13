class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] t = new int[nums.length];
        int size = 0;

        for(int x : nums){
            int i=0, j=size;
            while(i<j){
                int mid = i +(j-i)/2;
                if(t[mid] <x){
                    i=mid+ 1;
                }else{
                    j= mid;
                }
            }
            t[i] = x;
            if(i == size){
                size++;
            }
        }
        return size;
    }
}