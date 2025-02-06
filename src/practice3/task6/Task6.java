package practice3.task6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Task6 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String lineA = br.readLine();
        String lineB = br.readLine();

        solution(n, lineA, lineB);

        br.close();
    }

    private static void solution(int n, String lineA, String lineB) {
        Deque<Character> deque = new ArrayDeque<>();

        for (char c : lineB.toCharArray()) {
            if (isOpening(c)) {
                deque.push(getMatchingClosing(c));
            } else if (isClosing(c)) {
                deque.pop();
            }
        }

        int ost = n - lineB.length();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < ost; i++) {
            for (char c : lineA.toCharArray()) {
                if (isClosing(c)) {
                    if (!deque.isEmpty() && c == deque.peek()) {
                        sb.append(c);
                        deque.pop();
                        break;
                    }
                } else if (isOpening(c)) {
                    if (ost - i > deque.size()) {
                        sb.append(c);
                        deque.push(getMatchingClosing(c));
                        break;
                    }
                }
            }
        }

        System.out.println(lineB + sb);
    }

    private static char getMatchingClosing(char c) {
        if (c == '(') return ')';
        if (c == '[') return ']';

        return '?';
    }

    private static boolean isOpening(char c) {
        return c == '(' || c == '[';
    }

    private static boolean isClosing(char c) {
        return c == ')' || c == ']';
    }
 }



