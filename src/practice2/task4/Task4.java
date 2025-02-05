package practice2.task4;

import java.util.Arrays;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int[] deals = new int[n];

        for (int i = 0; i < n; i++) {
            deals[i] = scanner.nextInt();
        }

        solution(n, k, deals);

        scanner.close();
    }

    public static void solution(int n, int k, int[] deals) {
        int r = 0;
        int maxCount = 0;
        Arrays.sort(deals);

        for (int l = 0; l < n; l++) {
            while (r < n && deals[r] - deals[l] <= k) {
                r++;
            }

            maxCount = Math.max(maxCount, r - l);
        }

        System.out.println(maxCount);
    }
}

// 9 2
// 3 8 5 7 1 2 4 9 6

// 1 2 3 4 5 6 7 8 9
// 1 4 7