package practice2.task1;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] starter = new int[n];

        for (int i = 0; i < n; i++) {
            starter[i] = scanner.nextInt();
        }

        solution(n, starter);

        scanner.close();
    }

    public static void solution(int n, int[] starter) {
        int[] prefixSum = new int[n + 1];
        prefixSum[0] = 0;

        for (int i = 1; i < n + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + starter[i - 1];
        }

        for (int i = 1; i < prefixSum.length; i++) {
            System.out.print(prefixSum[i] + " ");
        }
    }
}
