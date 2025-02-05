package practice2.task7;

import java.util.Scanner;

public class Task7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        long c = scanner.nextLong();
        scanner.nextLine();

        String str = scanner.nextLine();

        solution(n, c, str);

        scanner.close();
    }

    public static void solution(int n, long c, String str) {
        long bad = 0;
        int l = 0;
        int cntA = 0;
        int cntB = 0;
        int maxLen = 0;

        for (int r = 0; r < n; r++) {
            char ch = str.charAt(r);
            if (ch == 'a') {
                cntA++;
            } else if (ch == 'b') {
                bad += cntA;
                cntB++;
            }

            while (bad > c && l <= r) {
                char chL = str.charAt(l);
                if (chL == 'a') {
                    bad -= cntB;
                    cntA--;
                } else if (chL == 'b'){
                    cntB--;
                }

                l++;
            }

            maxLen = Math.max(maxLen, r - l + 1);
        }

        System.out.println(maxLen);
    }
}
