package practice2.task6;

import java.util.Scanner;

public class Task6 {

    private static final long MOD = 1_000_000_007;
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

        long result = 0;

        for (int i = 1; i < n + 1; i++) {
            prefixSum[i] = (prefixSum[i - 1] + arr[i - 1]) % MOD;
        }

        for (int i = n - 1; i >= 0; i--) {
            suffixSum[i] = (suffixSum[i + 1] + arr[i]) % MOD;
        }

        for (int step = 1; step < n - 1; step++) {
            result = (result + (prefixSum[step] * arr[step] % MOD * suffixSum[step + 1] % MOD)) % MOD;
        }

        System.out.println(result);
    }
}
