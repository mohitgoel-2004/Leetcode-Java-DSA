class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(1, n, k, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int s, int n, int k, List<Integer> curr, List<List<Integer>> res ){
        if(curr.size() == k){
            res.add(new ArrayList<>(curr));
            return;
        }

        for(int i = s; i<= n - (k- curr.size()) + 1; i++){
            curr.add(i);
            backtrack(i+1,n,k,curr,res);
            curr.remove(curr.size() - 1);
        }
    }
}