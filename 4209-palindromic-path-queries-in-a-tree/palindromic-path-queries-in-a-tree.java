import java.util.*;

class Solution {
    int timer = 0;
    int[] tin, tout, head, depth, parentNode;
    int[][] up;
    int LOG;
    int[] tree;
    int[] lazy;
    int[] valMask;
    int[] initialPrefix;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        tin = new int[n];
        tout = new int[n];
        head = new int[n];
        depth = new int[n];
        parentNode = new int[n];
        initialPrefix = new int[n];
        LOG = 32 - Integer.numberOfLeadingZeros(n) + 1;
        up = new int[n][LOG];

        valMask = new int[n];
        for (int i = 0; i < n; i++) {
            valMask[i] = 1 << (s.charAt(i) - 'a');
        }

        // DFS to compute Euler tour, binary lifting table, and initial prefix XORs in O(N)
        dfs(0, 0, 0, 0, adj);

        tree = new int[4 * n];
        lazy = new int[4 * n];
        
        // Build segment tree in O(N) time
        build(1, 0, n - 1);

        List<Boolean> answer = new ArrayList<>();

        for (String qStr : queries) {
            String[] parts = qStr.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                int newMask = 1 << (parts[2].charAt(0) - 'a');
                int diff = valMask[u] ^ newMask;
                if (diff != 0) {
                    valMask[u] = newMask;
                    update(1, 0, n - 1, tin[u], tout[u], diff);
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int lca = getLCA(u, v);

                int prefixU = query(1, 0, n - 1, tin[u]);
                int prefixV = query(1, 0, n - 1, tin[v]);
                int prefixLCA = valMask[lca];

                int pathMask = prefixU ^ prefixV ^ prefixLCA;

                if (Integer.bitCount(pathMask) <= 1) {
                    answer.add(true);
                } else {
                    answer.add(false);
                }
            }
        }

        return answer;
    }

    private void dfs(int u, int p, int d, int currentXor, List<List<Integer>> adj) {
        tin[u] = timer++;
        head[tin[u]] = u;
        depth[u] = d;
        up[u][0] = p;
        parentNode[u] = p;
        
        currentXor ^= valMask[u];
        initialPrefix[u] = currentXor;

        for (int i = 1; i < LOG; i++) {
            up[u][i] = up[up[u][i - 1]][i - 1];
        }
        for (int v : adj.get(u)) {
            if (v != p) {
                dfs(v, u, d + 1, currentXor, adj);
            }
        }
        tout[u] = timer - 1;
    }

    private int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u; u = v; v = temp;
        }
        for (int i = LOG - 1; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[v]) {
                u = up[u][i];
            }
        }
        if (u == v) return u;
        for (int i = LOG - 1; i >= 0; i--) {
            if (up[u][i] != up[v][i]) {
                u = up[u][i];
                v = up[v][i];
            }
        }
        return up[u][0];
    }

    private void build(int node, int l, int r) {
        if (l == r) {
            int u = head[l];
            tree[node] = initialPrefix[u];
            return;
        }
        int mid = (l + r) / 2;
        build(2 * node, l, mid);
        build(2 * node + 1, mid + 1, r);
        tree[node] = tree[2 * node]; 
    }

    private void pushLazy(int node) {
        if (lazy[node] != 0) {
            lazy[2 * node] ^= lazy[node];
            tree[2 * node] ^= lazy[node];
            lazy[2 * node + 1] ^= lazy[node];
            tree[2 * node + 1] ^= lazy[node];
            lazy[node] = 0;
        }
    }

    private void update(int node, int l, int r, int ql, int qr, int val) {
        if (ql > r || qr < l) return;
        if (ql <= l && r <= qr) {
            tree[node] ^= val;
            lazy[node] ^= val;
            return;
        }
        pushLazy(node);
        int mid = (l + r) / 2;
        update(2 * node, l, mid, ql, qr, val);
        update(2 * node + 1, mid + 1, r, ql, qr, val);
    }

    private int query(int node, int l, int r, int idx) {
        if (l == r) return tree[node];
        pushLazy(node);
        int mid = (l + r) / 2;
        if (idx <= mid) {
            return query(2 * node, l, mid, idx);
        } else {
            return query(2 * node + 1, mid + 1, r, idx);
        }
    }
}