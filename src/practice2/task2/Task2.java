package practice2.task2;

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        solution(n, k, nums);

        scanner.close();
    }

    public static void solution(int n, int k, int[] nums) {
        int cntNums = 0;
        int curSum = 0;
        int r = 0;

        // 17 7 10 7 10
        // 17 -> +1
        // 7 -> 10 -> +1
        // 10 + 7 -> +1
        // 7 + 10 -> +1

        for (int l = 0; l < n; l++) {
            while (r < n && curSum < k) {
                curSum += nums[r];
                r++;
            }

            if (curSum == k) {
                cntNums++;
            }

            curSum -= nums[l];
        }

        System.out.println(cntNums);
    }
}
