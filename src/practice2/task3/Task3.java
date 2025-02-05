package practice2.task3;

import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int r = scanner.nextInt();

        int[] dist = new int[n];

        for (int i = 0; i < n; i++) {
            dist[i] = scanner.nextInt();
        }

        solution(n, r, dist);

        scanner.close();
    }

    public static void solution(int n, int r, int[] dist) {
        int labelR = 0;
        long cntOptions = 0;

        // 4 4
        // 1 3 5 8

//         6 3
//         1 2 4 7 8 12

        // sol:
        // 1 -> 7
        // cnt += 3
        // 2 -> 7
        // cnt += 3
        // 4 -> 8
        // cnt += 2
        // 7 -> 12
        // cnt += 1
        // 8 -> 12
        // cnt += 1
        // cnt = 10

        for (int labelL = 0; labelL < dist.length; labelL++) {
            while (labelR < n && dist[labelR] - dist[labelL] <= r) {
                labelR++;
            }

            cntOptions += n - labelR;
        }

        System.out.println(cntOptions);
    }
}
