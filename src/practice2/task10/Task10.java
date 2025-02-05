package practice2.task10;

import java.util.*;
import java.io.*;

public class Task10 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n + 1];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 1; i <= n; i++) a[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] queries = new int[m];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < m; i++) {
            queries[i] = Integer.parseInt(st.nextToken());
        }

        int LOG = 0;
        while ((1 << LOG) <= n) LOG++;

        int[][] dp = new int[n + 1][LOG];
        int[][] cost = new int[n + 1][LOG];

        dp[1][0] = 1;
        cost[1][0] = 0;

        for (int i = 2; i <= n; i++) {
            if (a[i - 1] <= a[i]) {
                dp[i][0] = i - 1;
                cost[i][0] = (a[i - 1] == a[i] ? 1 : 0);
            } else {
                dp[i][0] = i;
                cost[i][0] = 0;
            }
        }

        for (int j = 1; j < LOG; j++) {
            for (int i = 1; i <= n; i++) {
                int mid = dp[i][j - 1];
                dp[i][j] = dp[mid][j - 1];
                cost[i][j] = cost[i][j - 1] + cost[mid][j - 1];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int q : queries) {
            int pos = q;
            int eqUsed = 0;
            for (int j = LOG - 1; j >= 0; j--) {
                if (dp[pos][j] != pos && eqUsed + cost[pos][j] <= k) {
                    eqUsed += cost[pos][j];
                    pos = dp[pos][j];
                }
            }

            if (pos > 1 && a[pos - 1] <= a[pos]) {
                int c = (a[pos - 1] == a[pos] ? 1 : 0);
                if (eqUsed + c <= k) pos = pos - 1;
            }
            sb.append(pos).append(" ");
        }

        out.write(sb.toString().trim() + "\n");
        out.flush();
    }
}