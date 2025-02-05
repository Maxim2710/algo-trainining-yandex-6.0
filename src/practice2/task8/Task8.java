package practice2.task8;

import java.util.Arrays;
import java.util.Scanner;

public class Task8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        solution(n, arr);

        scanner.close();
    }

    public static void solution(int n, int[] arr) {
        long[] prefixSum = new long[n + 1];
        long[] suffixSum = new long[n + 1];

        for (int i = 1; i < n + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + arr[i];
        }

        long ans = 0;

        for (int i = 1; i < n; i++) {
            ans += (long) arr[i] * i;
        }

        long bestOption = ans;

        for (int step = 1; step < n; step++) {
            bestOption += prefixSum[step] - suffixSum[step];
            ans = Math.min(bestOption, ans);
        }

        System.out.println(ans);
    }
}
