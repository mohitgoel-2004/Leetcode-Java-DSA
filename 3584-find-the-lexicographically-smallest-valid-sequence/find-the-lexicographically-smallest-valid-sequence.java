class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[] lastMatch = new int[m + 1];
        lastMatch[m] = n;

        int ptr = n - 1;
        for (int i = m - 1; i >= 0; i--) {
            while (ptr >= 0 && word1.charAt(ptr) != word2.charAt(i)) {
                ptr--;
            }
            lastMatch[i] = ptr;
            if (ptr >= 0) {
                ptr--; // move to the left for the previous character match
            }
        }

        int[] result = new int[m];
        boolean changed = false;
        int idx = 0; // Current pointer in word1

        for (int i = 0; i < m; i++) {
            boolean found = false;
            while (idx < n) {
                // Scenario 1: Exact match
                if (word1.charAt(idx) == word2.charAt(i)) {
                    result[i] = idx;
                    idx++;
                    found = true;
                    break;
                } 
                // Scenario 2: Mismatch, but we haven't changed any character yet
                else if (!changed && lastMatch[i + 1] > idx) {
                    result[i] = idx;
                    idx++;
                    changed = true;
                    found = true;
                    break;
                }
                idx++;
            }

            if (!found) {
                return new int[0];
            }
        }

        return result;
    }
}