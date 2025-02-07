package practice4.task5;

import java.io.*;
import java.util.*;

public class Task5 {
    static ArrayList<Integer>[] adj;
    static int[] subtreeSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine().trim());

        adj = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i < V; i++) {
            String[] parts = br.readLine().split(" ");
            int u = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);
            adj[u].add(v);
            adj[v].add(u);
        }

        subtreeSize = new int[V + 1];

        dfs(1, 0);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            sb.append(subtreeSize[i]).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    static int dfs(int current, int parent) {
        int size = 1;
        for (int neighbor : adj[current]) {
            if (neighbor != parent) {
                size += dfs(neighbor, current);
            }
        }
        subtreeSize[current] = size;
        return size;
    }
}

