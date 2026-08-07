import java.util.Arrays;

class Solution {
    public String smallestNumber(String num, long t) {
        int[] req = getPrimeFactors(t);
        if (req == null) return "-1";

        int n = num.length();
        int[] numDigits = new int[n];
        for (int i = 0; i < n; i++) {
            numDigits[i] = num.charAt(i) - '0';
        }

        int firstZero = n;
        for (int i = 0; i < n; i++) {
            if (numDigits[i] == 0) {
                firstZero = i;
                break;
            }
        }

        int[][] prefixReq = new int[n + 1][4];
        prefixReq[0] = req.clone();

        for (int i = 0; i < Math.min(n, firstZero); i++) {
            prefixReq[i + 1] = removeFactor(prefixReq[i], numDigits[i]);
        }

        for (int i = Math.min(n, firstZero); i >= 0; i--) {
            int startDigit = (i == n) ? numDigits[n - 1] : (i < firstZero ? numDigits[i] + 1 : 1);
            if (i == n) {
                if (firstZero == n && isSatisfied(prefixReq[n])) {
                    return num;
                }
                continue;
            }

            for (int d = startDigit; d <= 9; d++) {
                int[] remReq = removeFactor(prefixReq[i], d);
                int remLen = n - 1 - i;
                int minDigits = getMinDigitsCount(remReq);

                if (minDigits <= remLen) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < i; j++) sb.append(numDigits[j]);
                    sb.append(d);
                    
                    String suffix = buildSuffix(remReq, remLen);
                    sb.append(suffix);
                    return sb.toString();
                }
            }
        }

        int minDigitsNeeded = getMinDigitsCount(req);
        int targetLen = Math.max(n + 1, minDigitsNeeded);
        return buildSuffix(req, targetLen);
    }

    private int[] getPrimeFactors(long t) {
        int[] counts = new int[4]; // 2, 3, 5, 7
        int[] primes = {2, 3, 5, 7};
        for (int i = 0; i < 4; i++) {
            while (t % primes[i] == 0) {
                counts[i]++;
                t /= primes[i];
            }
        }
        return t == 1 ? counts : null;
    }

    private int[] removeFactor(int[] req, int digit) {
        int[] res = req.clone();
        if (digit <= 1) return res;
        
        int temp = digit;
        int[] primes = {2, 3, 5, 7};
        for (int i = 0; i < 4; i++) {
            while (temp % primes[i] == 0) {
                res[i] = Math.max(0, res[i] - 1);
                temp /= primes[i];
            }
        }
        return res;
    }

    private boolean isSatisfied(int[] req) {
        return req[0] == 0 && req[1] == 0 && req[2] == 0 && req[3] == 0;
    }

    private int getMinDigitsCount(int[] req) {
        int c2 = req[0], c3 = req[1], c5 = req[2], c7 = req[3];
        int n9 = c3 / 2, r3 = c3 % 2;
        int n8 = c2 / 3, r2 = c2 % 3;
        int n7 = c7, n5 = c5;

        int n4 = (r2 == 2) ? 1 : 0;
        int n2 = (r2 == 1) ? 1 : 0;
        int n3 = r3;

        int total = n9 + n8 + n7 + n5 + n4 + n2 + n3;
        if (r2 == 1 && r3 == 1) {
            total--; // 2 and 3 combine into 6
        }
        return total;
    }

    private String buildSuffix(int[] req, int targetLen) {
        int c2 = req[0], c3 = req[1], c5 = req[2], c7 = req[3];
        int n9 = c3 / 2, r3 = c3 % 2;
        int n8 = c2 / 3, r2 = c2 % 3;
        int n7 = c7, n5 = c5;
        
        int n4 = 0, n2 = 0, n3 = 0, n6 = 0;

        if (r2 == 1 && r3 == 1) {
            n6 = 1;
        } else if (r2 == 2 && r3 == 1) {
            // FIX: Instead of 3 and 4, use 2 and 6 to put a smaller digit '2' first
            n2 = 1;
            n6 = 1;
        } else {
            n4 = (r2 == 2) ? 1 : 0;
            n2 = (r2 == 1) ? 1 : 0;
            n3 = r3;
        }

        int nonOnes = n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9;
        int n1 = targetLen - nonOnes;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n1; i++) sb.append('1');
        for (int i = 0; i < n2; i++) sb.append('2');
        for (int i = 0; i < n3; i++) sb.append('3');
        for (int i = 0; i < n4; i++) sb.append('4');
        for (int i = 0; i < n5; i++) sb.append('5');
        for (int i = 0; i < n6; i++) sb.append('6');
        for (int i = 0; i < n7; i++) sb.append('7');
        for (int i = 0; i < n8; i++) sb.append('8');
        for (int i = 0; i < n9; i++) sb.append('9');

        return sb.toString();
    }
}