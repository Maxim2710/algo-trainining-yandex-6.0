package practice2.task5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] medians = new int[n];

        for (int i = 0; i < n; i++) {
            medians[i] = scanner.nextInt();
        }

        solution(n, medians);

        scanner.close();
    }

    public static void solution(int n, int[] medians) {
        int r = n / 2;
        int l = n / 2 - 1;

        Arrays.sort(medians);
        List<Integer> result = new ArrayList<>();

        while (result.size() < n) {
            if ((n - result.size()) % 2 == 1) {
                result.add(medians[r]);
                r++;
            } else {
                result.add(medians[l]);
                l--;
            }
        }

        for (int el : result) {
            System.out.print(el + " ");
        }
    }
}

// 3
// 19 3 8
// 3 8 19