package practice3.task10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Task10 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[] heights = new int[n];
        int[] widths = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            widths[i] = Integer.parseInt(st.nextToken());
        }

        PrintWriter out = new PrintWriter(System.out);

        solution(n, h, heights, widths, out);
        out.flush();

        br.close();
    }

    private static void solution(int n, int h, int[] heights, int[] widths, PrintWriter out) {
        Deque<Integer> deque = new ArrayDeque<>();

        List<int[]> hw = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            hw.add(new int[]{heights[i], widths[i]});
        }

        hw.sort(Comparator.comparingInt(o -> o[0]));

        int ans = hw.getLast()[0] - hw.getFirst()[0];
        int nowLen = hw.getFirst()[1];
        int r = 1;

        for (int[] chair : hw) {
            if (chair[1] >= h) {
                out.println(0);
                return;
            }
        }

        for (int l = 0; l < n - 1; l++) {
            while (r < n && nowLen < h) {
                nowLen += hw.get(r)[1];

                while (!deque.isEmpty() && deque.getLast() < hw.get(r)[0] - hw.get(r - 1)[0]) {
                    deque.removeLast();
                }

                // Выше считаем максимальную разность между высотами - т.к. если разность между ними такая
                // то меньшие разности уже никакой роли не играют

                deque.addLast(hw.get(r)[0] - hw.get(r - 1)[0]);
                r++;
            }

            if (nowLen >= h && !deque.isEmpty()) {
                ans = Math.min(ans, deque.peekFirst());
            }

            nowLen -= hw.get(l)[1];
            if (r > l + 1) {
                if (!deque.isEmpty() && deque.peekFirst() == hw.get(l + 1)[0] - hw.get(l)[0]) {
                    deque.removeFirst();
                }
            }
        }

        out.println(ans);
    }
}
