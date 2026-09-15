class Solution {
    public int maxPalindromes(String s, int k) {
        int cnt = 0;
        int n = s.length();
        int lastEnd = -1;

        for (int center = 0; center < 2 * n - 1; center++) {
            int left = center / 2;
            int right = left + (center % 2);

            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                int len = right - left + 1;

               
                if (len >= k && left > lastEnd) {
                    cnt++;
                    lastEnd = right;
                    break; 
                }

                if (len > k + 1) {
                    break;
                }

                left--;
                right++;
            }
        }

        return cnt;
    }
}