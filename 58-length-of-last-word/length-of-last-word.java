class Solution {
    public int lengthOfLastWord(String s) {
        String str = s.trim(); // Remove trailing/leading spaces
        int count = 0;

        // Loop from the last character of trimmed string
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) != ' ') {
                count++;
            } else {
                break; // Stop when first space is found after last word
            }
        }
        return count; // Return word length
    }
}