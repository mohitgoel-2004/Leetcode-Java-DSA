class Solution {
    public boolean uniformArray(int[] nums1) {
        int min = Integer.MAX_VALUE;
        int minOdd = Integer.MAX_VALUE;

        for(int num: nums1){
            min = Math.min(min, num);
            if(num % 2 != 0){
                minOdd = Math.min(minOdd, num);
            }
        }

        if(minOdd == Integer.MAX_VALUE){
            return true;
        }
        return min == minOdd;
    }
}