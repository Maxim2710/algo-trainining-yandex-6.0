package practice2.task9;

import java.io.*;
import java.util.*;

public class Task9 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        int[] a = new int[n];
        int[] b = new int[n];
        int[] p = new int[n];

        String[] parts = br.readLine().trim().split("\\s+");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(parts[i]);
        }
        parts = br.readLine().trim().split("\\s+");
        for (int i = 0; i < n; i++) {
            b[i] = Integer.parseInt(parts[i]);
        }
        parts = br.readLine().trim().split("\\s+");
        for (int i = 0; i < n; i++) {
            p[i] = Integer.parseInt(parts[i]);
        }
        solution(n, a, b, p);
    }

    public static void solution(int n, int[] a, int[] b, int[] p) {
        int[][] sortedA = new int[n][3];
        int[][] sortedB = new int[n][3];
        for (int i = 0; i < n; i++) {
            sortedA[i][0] = a[i];
            sortedA[i][1] = b[i];
            sortedA[i][2] = i;
            sortedB[i][0] = a[i];
            sortedB[i][1] = b[i];
            sortedB[i][2] = i;
        }

        Arrays.sort(sortedA, new Comparator<int[]>() {
            @Override
            public int compare(int[] x, int[] y) {
                if (x[0] != y[0]) return Integer.compare(y[0], x[0]);
                if (x[1] != y[1]) return Integer.compare(y[1], x[1]);
                return Integer.compare(x[2], y[2]);
            }
        });

        Arrays.sort(sortedB, new Comparator<int[]>() {
            @Override
            public int compare(int[] x, int[] y) {
                if (x[1] != y[1]) return Integer.compare(y[1], x[1]);
                if (x[0] != y[0]) return Integer.compare(y[0], x[0]);
                return Integer.compare(x[2], y[2]);
            }
        });

        int aPos = 0, bPos = 0;
        boolean[] used = new boolean[n];
        int[] result = new int[n];
        int resultIndex = 0;

        for (int i = 0; i < n; i++) {
            int algo;
            if (p[i] == 0) {
                while (aPos < n && used[sortedA[aPos][2]]) aPos++;
                algo = sortedA[aPos][2];
                aPos++;
            } else {
                while (bPos < n && used[sortedB[bPos][2]]) bPos++;
                algo = sortedB[bPos][2];
                bPos++;
            }
            used[algo] = true;
            result[resultIndex++] = algo + 1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(result[i]);
            if (i < n - 1) sb.append(" ");
        }
        System.out.println(sb.toString());
    }
}
