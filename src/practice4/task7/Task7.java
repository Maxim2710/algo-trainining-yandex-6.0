package practice4.task7;

import java.io.*;
import java.util.*;

public class Task7 {
    static long ans;
    static boolean bad;
    static long[] factorial;
    static int mod;
    static ArrayList<Integer>[] g;
    static boolean[] visited;

    static int getAns(int num, boolean isRoot, int father) {
        if (bad) return 0;

        if (isRoot && g[num].size() == 1 && g[g[num].get(0)].size() > 1) {
            getAns(g[num].get(0), true, -1);
            return 0;
        }

        visited[num] = true;
        int cntBigChilds = 0;
        int subtreeSize = 1;

        for (int child : g[num]) {
            if (bad) return 0;

            if (visited[child] && child != father) {
                bad = true;
                return 0;
            } else if (!visited[child]) {
                int cSize = getAns(child, false, num);
                subtreeSize += cSize;

                if (cSize >= 2) cntBigChilds++;

                if ((isRoot && cntBigChilds > 2) || (!isRoot && cntBigChilds > 1)) {
                    bad = true;
                    return 0;
                }
            }
        }

        int cntSmallChilds = g[num].size() - cntBigChilds - 1 + (isRoot ? 1 : 0);
        ans = (ans * factorial[cntSmallChilds]) % mod;

        if (isRoot) {
            ans = (ans * 2) % mod;

            if ((cntSmallChilds > 0 && cntBigChilds > 0) || (cntBigChilds == 2))
                ans = (ans * 2) % mod;
        }

        return subtreeSize;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().trim().split("\\s+");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);
        mod = Integer.parseInt(firstLine[2]);

        if (m > n - 1) {
            System.out.println(0);
            return;
        }

        factorial = new long[n + 1];
        factorial[0] = 1;

        if (n >= 1) {
            factorial[1] = 1;
        }

        for (int i = 2; i <= n; i++) {
            factorial[i] = (factorial[i - 1] * i) % mod;
        }

        g = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            g[i] = new ArrayList<>();
        }

        bad = false;
        ans = 1;
        int lonely = 0;
        int trees = 0;
        visited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            String[] edgeLine = br.readLine().trim().split("\\s+");
            int a = Integer.parseInt(edgeLine[0]);
            int b = Integer.parseInt(edgeLine[1]);
            g[a].add(b);
            g[b].add(a);
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i] && g[i].size() > 0) {
                trees++;
                getAns(i, true, -1);
            }

            if (g[i].isEmpty()) {
                lonely++;
            }
        }

        if (bad) {
            System.out.println(0);
        } else {
            ans = (ans * factorial[trees]) % mod;
            int notLonely = n - lonely;

            for (int i = 0; i < lonely; i++) {
                ans = (ans * (2L + notLonely + i)) % mod;
            }
            System.out.println(ans);
        }
    }
}
