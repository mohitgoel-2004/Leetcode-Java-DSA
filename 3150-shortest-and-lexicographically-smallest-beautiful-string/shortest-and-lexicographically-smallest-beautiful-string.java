class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        String ans = "";

        List<Integer> ls = new ArrayList<>();
        for(int i=0; i<n ; i++){
            if(s.charAt(i) == '1'){
                ls.add(i);
            }
        }
        if(ls.size() < k){
            return "";
        }
        for(int i=0; i<=ls.size() -k; i++){
            int start = ls.get(i);
            int end = ls.get(i+k-1);

            String candidate = s.substring(start, end + 1);

            if(ans.isEmpty() || candidate.length() < ans.length() || ( candidate.length() == ans.length() && candidate.compareTo(ans) < 0)){
                ans = candidate;
            }
        }
        return ans;
    }
}