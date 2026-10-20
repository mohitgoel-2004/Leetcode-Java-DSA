import java.util.Arrays;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean possible = true;

            for (int start = 0; start < n; start += k) {
                if (!canRotate(nums, sorted, start, k)) {
                    possible = false;
                    break;
                }
            }

            if (possible) {
                ans += k;
            }
        }

        return ans;
    }

    private boolean canRotate(int[] nums, int[] sorted,
                              int start, int k) {
        // Build KMP prefix array for the target block.
        int[] lps = new int[k];

        for (int i = 1, len = 0; i < k; ) {
            if (sorted[start + i] == sorted[start + len]) {
                lps[i++] = ++len;
            } else if (len > 0) {
                len = lps[len - 1];
            } else {
                lps[i++] = 0;
            }
        }

        // Search for the target block in the cyclic source block.
        // Two copies represent all cyclic rotations.
        int j = 0;

        for (int i = 0; i < 2 * k - 1; i++) {
            int value = nums[start + (i % k)];

            while (j > 0 && value != sorted[start + j]) {
                j = lps[j - 1];
            }

            if (value == sorted[start + j]) {
                j++;
            }

            if (j == k) {
                return true;
            }
        }

        return false;
    }
}