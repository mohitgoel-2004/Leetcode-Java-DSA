class Solution {
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        // Array to store the 1-based index of the last seen position of each ASCII character
        int[] lastSeen = new int[128]; 

        for (int right = 0, left = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // Move the left pointer past the previous index of currentChar if it's inside the window
            left = Math.max(left, lastSeen[currentChar]);

            // Calculate max window length
            maxLength = Math.max(maxLength, right - left + 1);

            // Store (right + 1) to mark the index after the current position
            lastSeen[currentChar] = right + 1;
        }

        return maxLength;
    }
}