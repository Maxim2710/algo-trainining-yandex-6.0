package practice3.task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Task3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] nums = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        solution(n, k, nums);

        br.close();
    }

    public static void solution(int n, int k, int[] nums) {
        Deque<Integer> deque = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {

            // если одно k прошли и начали следующий круг - удаляем минимум
            if (!deque.isEmpty() && i >= k && deque.peekFirst() == nums[i - k]) {
                deque.pollFirst();
            }

            // если стек не пустой и нашли меньше, чем последний - удаляем все
            while (!deque.isEmpty() && nums[i] < deque.peekLast()) {
                deque.pollLast();
            }

            // иначе просто добавим в конец
            deque.addLast(nums[i]);

            if (i >= k - 1) {
                sb.append(deque.peekFirst()).append("\n");
            }
        }

        System.out.println(sb);


    }
}

// 7 3
// 1 3 2 4 5 3 1

// line-check: 1 3
// deque: 3
// deleted: 3 4 5
// ans: 1 2 2 3 1

// 4 5
// 1 2 2
