class Solution {
    public boolean canJump(int[] nums) {
        int maxReachable = 0;
        
        for (int i = 0; i < nums.length; i++) {
            // If current position is beyond the maximum reachable index, return false
            if (i > maxReachable) {
                return false;
            }
            
            // Update the furthest index we can reach
            maxReachable = Math.max(maxReachable, i + nums[i]);
            
            // Optimization: If we can reach or exceed the last index, return true early
            if (maxReachable >= nums.length - 1) {
                return true;
            }
        }
        
        return true;
    }
}