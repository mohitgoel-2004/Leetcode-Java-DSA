class Solution {
    public int jump(int[] nums) {
        int j =0;
        int curr = 0;
        int f = 0;

        for(int i=0; i<nums.length - 1;i++){
            f = Math.max(f, i+nums[i]);

            if(i==curr){
                j++;
                curr = f;

                if(curr >= nums.length-1){
                    break;
                }
            }
        }
        return j;
    }
}