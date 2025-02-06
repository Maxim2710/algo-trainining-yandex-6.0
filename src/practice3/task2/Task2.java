package practice3.task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Task2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
//        Scanner scanner = new Scanner(System.in);
//
//        int n = scanner.nextInt();
//        long[] arr = new long[n];
//
//        for (int i = 0; i < n; i++) {
//            arr[i] = scanner.nextLong();
//        }

        solution(n, arr);

        br.close();
    }

    public static void solution(int n, long[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        long[] answer = new long[n];
        Arrays.fill(answer, -1);

        for (int i = 0; i < n; i++) {
            while(!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                answer[stack.pop()] = i;
            }

            stack.push(i);
        }

        for (long num : answer) {
            System.out.print(num + " ");
        }

    }
}


// 1 2 3 2 1 4 2 5 3 1

// ans: -1 4 3 4 -1 6 9 8 9 -1
//
// stack: 2 3
