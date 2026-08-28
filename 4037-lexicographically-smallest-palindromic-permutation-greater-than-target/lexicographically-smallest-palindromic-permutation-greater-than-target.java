import java.util.*;

class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Check if a palindromic permutation is possible
        int oddCount = 0;
        int oddChar = -1;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                oddChar = i;
            }
        }
        if (oddCount > 1) return "";

        int m = n / 2;
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        // Try matching target's first half up to length L (from m down to 0)
        for (int L = m; L >= 0; L--) {
            int[] remHalf = halfCount.clone();
            boolean possible = true;
            char[] firstHalf = new char[m];

            // 1. Fill exact prefix up to L - 1 matching target
            for (int i = 0; i < L; i++) {
                int c = target.charAt(i) - 'a';
                if (remHalf[c] > 0) {
                    remHalf[c]--;
                    firstHalf[i] = (char) ('a' + c);
                } else {
                    possible = false;
                    break;
                }
            }
            if (!possible) continue;

            // 2. At position L:
            // If L == m, we match the entire first half and check if middle/mirror makes it strictly greater
            if (L == m) {
                String candidate = buildPalindrome(firstHalf, remHalf, oddChar, n);
                if (candidate.compareTo(target) > 0) {
                    return candidate;
                }
                continue;
            }

            // If L < m, pick a character > target.charAt(L)
            int targetChar = target.charAt(L) - 'a';
            for (int c = targetChar + 1; c < 26; c++) {
                if (remHalf[c] > 0) {
                    int[] currentRem = remHalf.clone();
                    currentRem[c]--;
                    firstHalf[L] = (char) ('a' + c);

                    // Fill the rest of the first half with the smallest available characters
                    int idx = L + 1;
                    for (int ch = 0; ch < 26; ch++) {
                        while (currentRem[ch] > 0) {
                            firstHalf[idx++] = (char) ('a' + ch);
                            currentRem[ch]--;
                        }
                    }

                    String candidate = buildPalindrome(firstHalf, currentRem, oddChar, n);
                    if (candidate.compareTo(target) > 0) {
                        return candidate;
                    }
                }
            }
        }

        return "";
    }

    private String buildPalindrome(char[] firstHalf, int[] remHalf, int oddChar, int n) {
        StringBuilder sb = new StringBuilder();
        sb.append(firstHalf);
        if (n % 2 != 0) {
            sb.append((char) ('a' + oddChar));
        }
        for (int i = firstHalf.length - 1; i >= 0; i--) {
            sb.append(firstHalf[i]);
        }
        return sb.toString();
    }
}