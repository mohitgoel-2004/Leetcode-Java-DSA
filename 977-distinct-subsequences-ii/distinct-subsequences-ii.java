class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        long[] last = new long[26];

        for (char c: s.toCharArray()){
            int idx = c-'a';

            long curr = 0;
            for (int i=0; i<26; i++){
                curr = (curr + last[i] )% MOD;
            }
            last[idx] = (curr + 1) % MOD;
        }

        long res =0;
        for(int i=0; i<26; i++){
            res = (res + last[i]) % MOD;
        }
        return (int) res;
    }
}