class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> p1 = new ArrayList<>();
        List<int[]> p2 = new ArrayList<>();

        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                if(img1[r][c] ==1) p1.add(new int[]{r,c});
                if(img2[r][c]==1)p2.add(new int[]{r,c});
            }
        }
        Map<String, Integer> count = new HashMap<>();
        int maxOverlap = 0;
        
        for (int[] a : p1) {
            for (int[] b : p2) {
                int dr = b[0] - a[0];
                int dc = b[1] - a[1];
                
                String key = dr + "," + dc;
                int currentCount = count.getOrDefault(key, 0) + 1;
                count.put(key, currentCount);
                
                maxOverlap = Math.max(maxOverlap, currentCount);
            }
        }
        
        return maxOverlap;
    }
}