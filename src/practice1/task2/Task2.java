package practice1.task2;

import java.util.Scanner;

public class Task2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int A = scanner.nextInt();
        int B = scanner.nextInt();
        int C = scanner.nextInt();
        int D = scanner.nextInt();

        Task2.solution(A, B, C, D);

        scanner.close();
    }

    private static void solution(int a, int b, int c, int d) {
        int minSum = Integer.MAX_VALUE;
        int M = 0, N = 0;

        if(a > 0 && b > 0) {
            int mikes1 = Math.max(a, b) + 1;
            int socks1 = 1;
            int sum1 = mikes1 + socks1;
            if(sum1 < minSum) {
                minSum = sum1;
                M = mikes1;
                N = socks1;
            }
        }

        if(c > 0 && d > 0) {
            int mikes2 = 1;
            int socks2 = Math.max(c, d) + 1;
            int sum2 = mikes2 + socks2;
            if(sum2 < minSum) {
                minSum = sum2;
                M = mikes2;
                N = socks2;
            }
        }

        if(a > 0 && c > 0) {
            int mikes3 = b + 1;
            int socks3 = d + 1;
            int sum3 = mikes3 + socks3;
            if(sum3 < minSum) {
                minSum = sum3;
                M = mikes3;
                N = socks3;
            }
        }

        if(b > 0 && d > 0) {
            int mikes4 = a + 1;
            int socks4 = c + 1;
            int sum4 = mikes4 + socks4;
            if(sum4 < minSum) {
                minSum = sum4;
                M = mikes4;
                N = socks4;
            }
        }

        System.out.println(M + " " + N);
    }
}
