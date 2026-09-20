class Solution {
    public int reverseDegree(String s) {
        int totalSum = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // Position in reversed alphabet: 'a' -> 26, 'b' -> 25, ..., 'z' -> 1
            int reversedAlphabetPos = 26 - (s.charAt(i) - 'a');
            
            // 1-indexed position in string
            int stringPos = i + 1;
            
            totalSum += reversedAlphabetPos * stringPos;
        }
        
        return totalSum;
    }
}