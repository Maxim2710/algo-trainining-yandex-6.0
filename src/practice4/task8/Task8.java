package practice4.task8;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Task8 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        while (line != null && line.trim().isEmpty()){
            line = br.readLine();
        }
        assert line != null;
        int n = Integer.parseInt(line.trim());

        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for (int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++){
            String s = br.readLine();
            while (s != null && s.trim().isEmpty()){
                s = br.readLine();
            }
            StringTokenizer st = new StringTokenizer(s);
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        String sCosts = br.readLine();
        while (sCosts != null && sCosts.trim().isEmpty()){
            sCosts = br.readLine();
        }

        assert sCosts != null;
        StringTokenizer stCosts = new StringTokenizer(sCosts);
        long[] cost = new long[n+1];
        for (int i = 1; i <= n; i++){
            cost[i] = Long.parseLong(stCosts.nextToken());
        }

        long[] dp0 = new long[n+1];
        long[] dp1 = new long[n+1];

        int[] parent = new int[n+1];
        int[] pos = new int[n+1];

        int[] stack = new int[n];
        int top = 0;
        stack[0] = 1;
        parent[1] = 0;
        Arrays.fill(pos, 0);

        while(top >= 0){
            int v = stack[top];
            if (pos[v] < graph[v].size()){
                int u = graph[v].get(pos[v]);
                pos[v]++;
                if (u == parent[v]) continue;
                parent[u] = v;
                top++;
                stack[top] = u;
            } else {

                top--;
                dp0[v] = 0;
                dp1[v] = cost[v];
                for (int u : graph[v]) {
                    if (u == parent[v]) continue;
                    dp0[v] += dp1[u];
                    dp1[v] += Math.min(dp0[u], dp1[u]);
                }
            }
        }

        long bestCost = (n == 1) ? dp1[1] : Math.min(dp0[1], dp1[1]);

        boolean[] chosen = new boolean[n+1];
        if (n == 1) {
            chosen[1] = true;
        } else {
            chosen[1] = (dp1[1] < dp0[1]);
        }

        int[] stack2 = new int[n];
        int top2 = 0;
        stack2[0] = 1;
        while (top2 >= 0){
            int v = stack2[top2];
            top2--;
            for (int u : graph[v]) {
                if (u == parent[v]) continue;
                if (!chosen[v]){
                    chosen[u] = true;
                } else {
                    chosen[u] = (dp1[u] < dp0[u]);
                }
                top2++;
                stack2[top2] = u;
            }
        }

        int countChosen = 0;
        for (int i = 1; i <= n; i++){
            if(chosen[i])
                countChosen++;
        }

        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        out.println(bestCost + " " + countChosen);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++){
            if(chosen[i])
                sb.append(i).append(" ");
        }
        out.println(sb.toString().trim());
        out.flush();
    }
}

