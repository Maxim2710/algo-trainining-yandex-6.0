package practice4.task6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class Task6 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        ArrayList<Integer>[] children = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            children[i] = new ArrayList<>();
        }
        if (n > 1) {
            String[] bosses = br.readLine().split("\\s+");
            for (int i = 2; i <= n; i++) {
                int boss = Integer.parseInt(bosses[i - 2]);
                children[boss].add(i);
            }
        }
        long[] T = new long[n + 1];
        long[] S = new long[n + 1];
        for (int i = n; i >= 1; i--) {
            T[i] = 1;
            S[i] = 1;
            for (int child : children[i]) {
                T[i] += T[child];
                S[i] += T[child] + S[child];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(S[i]).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}

