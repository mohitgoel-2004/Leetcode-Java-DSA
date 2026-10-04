class Solution {
    public boolean checkValidString(String s) {
        int minO = 0;
        int maxO = 0;

        for(char c : s.toCharArray()){
            if(c == '('){
                minO++;
                maxO++;
            }else if(c == ')'){
                 minO--;
                maxO--;
            }else{
                 minO--;
                maxO++;
            }
            if(maxO <0){
                return false;
            }
            if(minO <0){
                minO = 0;
            }
        }
        return minO == 0;
    }
}