package practice4.task10;

import java.util.*;

public class Task10 {
    static final int MOD = 1000000007;
    static List<List<int[]>> g;
    static int[][] cnk;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        g = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            g.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            g.get(u).add(new int[]{v, 0});
            g.get(v).add(new int[]{u, 1});
        }

        cnk = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            cnk[i][0] = 1;

            for (int j = 1; j <= i; j++) {
                cnk[i][j] = (cnk[i - 1][j - 1] + cnk[i - 1][j]) % MOD;
            }
        }

        int[] ans = countTopsorts(1, -1);
        long sum = 0;

        for (int x : ans) {
            sum = (sum + x) % MOD;
        }

        System.out.println(sum);
    }

    static int[] countTopsorts(int num, int parent) {
        int[] d1 = new int[]{1};

        for (int[] child : g.get(num)) {
            if (child[0] == parent) continue;
            int[] nd = countTopsorts(child[0], num);
            int[] nds = new int[nd.length + 1];

            for (int i = 0; i < nd.length; i++) {
                nds[i + 1] = (nds[i] + nd[i]) % MOD;
            }

            int[] d2 = new int[d1.length + nd.length];

            for (int x1 = 0; x1 < d1.length; x1++) {
                for (int cnt1 = 0; cnt1 <= nd.length; cnt1++) {
                    int x2 = x1 + cnt1;
                    long val = (long) cnk[x2][cnt1] * cnk[d2.length - 1 - x2][nd.length - cnt1] % MOD * d1[x1] % MOD;

                    if (child[1] == 1) {
                        val = val * nds[cnt1] % MOD;
                    } else {
                        val = val * (nds[nd.length] - nds[cnt1] + MOD) % MOD;
                    }

                    d2[x2] = (int) ((d2[x2] + val) % MOD);
                }
            }

            d1 = d2;
        }

        return d1;
    }
}
