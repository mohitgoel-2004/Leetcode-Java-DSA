import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        
        // Store [l, r, weight, original_index]
        int[][] sorted = new int[n][4];
        for (int i = 0; i < n; i++) {
            sorted[i][0] = intervals.get(i).get(0);
            sorted[i][1] = intervals.get(i).get(1);
            sorted[i][2] = intervals.get(i).get(2);
            sorted[i][3] = i;
        }

        // Sort by right endpoint (r) ascending
        Arrays.sort(sorted, (a, b) -> Integer.compare(a[1], b[1]));

        // Precompute the latest non-overlapping interval index for each interval
        int[] prev = new int[n];
        for (int i = 0; i < n; i++) {
            int left = 0, right = i - 1, res = -1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (sorted[mid][1] < sorted[i][0]) {
                    res = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            prev[i] = res;
        }

        // dp[k][i] = BestState at step k considering up to i-th interval
        BestState[][] dp = new BestState[5][n + 1];
        for (int k = 0; k <= 4; k++) {
            for (int i = 0; i <= n; i++) {
                dp[k][i] = new BestState(0, new ArrayList<>());
            }
        }

        for (int k = 1; k <= 4; k++) {
            for (int i = 1; i <= n; i++) {
                // Option 1: Do not pick interval i-1
                BestState best = dp[k][i - 1];

                // Option 2: Pick interval i-1
                int p = prev[i - 1];
                BestState prevBest = dp[k - 1][p + 1];
                long candidateWeight = prevBest.weight + sorted[i - 1][2];

                List<Integer> candidateIndices = new ArrayList<>(prevBest.indices);
                candidateIndices.add(sorted[i - 1][3]);
                Collections.sort(candidateIndices);

                BestState candidateState = new BestState(candidateWeight, candidateIndices);
                
                // Compare Option 1 and Option 2
                dp[k][i] = BestState.getBetter(best, candidateState);
            }
        }

        // Find the absolute best among k = 1..4
        BestState ansState = dp[1][n];
        for (int k = 2; k <= 4; k++) {
            ansState = BestState.getBetter(ansState, dp[k][n]);
        }

        int[] result = new int[ansState.indices.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = ansState.indices.get(i);
        }

        return result;
    }

    private static class BestState {
        long weight;
        List<Integer> indices;

        BestState(long weight, List<Integer> indices) {
            this.weight = weight;
            this.indices = indices;
        }

        public static BestState getBetter(BestState a, BestState b) {
            if (a.weight > b.weight) return a;
            if (b.weight > a.weight) return b;

            // Ties broken lexicographically
            int len = Math.min(a.indices.size(), b.indices.size());
            for (int i = 0; i < len; i++) {
                if (!a.indices.get(i).equals(b.indices.get(i))) {
                    return a.indices.get(i) < b.indices.get(i) ? a : b;
                }
            }
            return a.indices.size() <= b.indices.size() ? a : b;
        }
    }
}