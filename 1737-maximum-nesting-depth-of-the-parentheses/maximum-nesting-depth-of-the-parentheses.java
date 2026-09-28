class Solution {
    public int maxDepth(String s) {
        int curr = 0;
        int depth = 0;

        for(char c : s.toCharArray()){
            if(c == '('){
                curr++;
                depth = Math.max(depth, curr);
            } else if(c == ')'){
                curr --;
            }
        }
        return depth;
    }
}