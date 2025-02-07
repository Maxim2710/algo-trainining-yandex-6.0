package practice4.task9;

import java.util.*;
import java.io.*;

public class Task9 {
    static int n;
    static ArrayList<Integer>[] g;
    static ArrayList<Pair>[] top3depth;
    static int[] depth;
    static int[] upath;

    static class Pair implements Comparable<Pair> {
        int depth;
        int node;

        public Pair(int depth, int node) {
            this.depth = depth;
            this.node = node;
        }

        @Override
        public int compareTo(Pair other) {
            return Integer.compare(other.depth, this.depth);
        }
    }

    static class Result {
        long ans;
        long maxPath;

        public Result(long ans, long maxPath) {
            this.ans = ans;
            this.maxPath = maxPath;
        }
    }

    static int calcDepth(int num, int father) {
        if ((father != -1 && g[num].size() == 1) || (father == -1 && g[num].isEmpty())) {
            depth[num] = 1;
            return 1;
        }

        depth[num] = 1;

        for (int son : g[num]) {

            if (son == father) continue;

            int childDepth = calcDepth(son, num);
            depth[num] = Math.max(depth[num], childDepth + 1);
            top3depth[num].add(new Pair(childDepth, son));

            Collections.sort(top3depth[num]);

            if (top3depth[num].size() > 3) {
                while (top3depth[num].size() > 3) {
                    top3depth[num].remove(top3depth[num].size() - 1);
                }
            }
        }

        return depth[num];
    }

    static void calcUpperPath(int num, int father, int outerPath, int innerPath) {
        upath[num] = Math.max(innerPath, outerPath);

        for (int son : g[num]) {

            if (son == father) continue;
            int sonInnerPath = innerPath + 1;
            int sonOuterPath = outerPath;

            for (Pair p : top3depth[num]) {
                if (p.node != son) {
                    sonOuterPath = Math.max(outerPath, sonInnerPath + p.depth);
                    sonInnerPath = Math.max(innerPath + 1, p.depth + 1);
                    break;
                }
            }

            calcUpperPath(son, num, sonOuterPath, sonInnerPath);
        }
    }

    static Result calcInSubtree(int num, int father) {
        long ans = 0;
        long maxPath = 0;

        for (int son : g[num]) {

            if (son == father) continue;

            Result childRes = calcInSubtree(son, num);
            ans = Math.max(ans, childRes.ans);
            int cntUsed = 0;
            long sumSonDepths = 0;

            for (Pair p : top3depth[num]) {
                if (cntUsed < 2 && p.node != son) {
                    cntUsed++;
                    sumSonDepths += p.depth;
                }
            }

            ans = Math.max(ans, childRes.maxPath * sumSonDepths);
        }

        int limit = Math.min(2, top3depth[num].size());

        for (int i = 0; i < limit; i++) {
            maxPath += top3depth[num].get(i).depth;
        }

        ans = Math.max(ans, (upath[num] - 1L) * maxPath);
        return new Result(ans, maxPath);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());

        depth = new int[n + 1];
        upath = new int[n + 1];
        g = new ArrayList[n + 1];
        top3depth = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            g[i] = new ArrayList<>();
            top3depth[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            g[u].add(v);
            g[v].add(u);
        }

        calcDepth(1, -1);
        calcUpperPath(1, -1, 0, 0);
        Result res = calcInSubtree(1, -1);
        System.out.println(res.ans);
    }
}
