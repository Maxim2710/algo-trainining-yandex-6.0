package practice3.task7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task7 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] queue = new int[n];
        for (int i = 0; i < n; i++) {
            queue[i] = Integer.parseInt(st.nextToken());
        }

        solution(n, b, queue);

        br.close();
    }

    private static void solution(int n, int b, int[] queue) {
        long answer = 0;
        int curQueue = 0;
        for (int q : queue) {
            curQueue += q;
            answer += curQueue;
            curQueue -= Math.min(b, curQueue);
        }

        answer += curQueue;

        System.out.println(answer);
    }
}
