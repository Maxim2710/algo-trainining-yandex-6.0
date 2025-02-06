package practice3.task1;

import java.util.Scanner;
import java.util.Stack;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();

        solution(line);

        scanner.close();
    }

    public static void solution(String line) {
        Stack<Character> stack = new Stack<>();

        for (char c : line.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '{') stack.push('}');
            else if (c == '[') stack.push(']');
            else if (stack.isEmpty() || c != stack.pop()) {
                System.out.println("no");
                return;
            }
        }

        if (!stack.isEmpty()) {
            System.out.println("no");
            return;
        }

        System.out.println("yes");
    }
}
