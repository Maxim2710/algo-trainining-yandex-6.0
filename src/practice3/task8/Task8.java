package practice3.task8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Task8 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Long> listSum = new ArrayList<>();
        listSum.add(0L);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            char op = line.charAt(0);

            if (op == '+') {
                long num = Long.parseLong(line.substring(1).trim());
                listSum.add(listSum.getLast() + num);
            } else if (op == '-') {
                sb.append(listSum.getLast() - listSum.get(listSum.size() - 2)).append("\n");
                listSum.removeLast();
            } else if (op == '?') {
                int num = Integer.parseInt(line.substring(1).trim());
                sb.append(listSum.getLast() - listSum.get(listSum.size() - 1 - num)).append("\n");
            }
        }

        System.out.println(sb);
    }
}
