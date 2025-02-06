package practice3.task4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Task4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine().trim();

        String[] tokens = line.split("\\s+");

        solution(tokens);

        br.close();
    }

    public static void solution(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            switch(token) {
                case "+":
                    int bAdd = stack.pop();
                    int aAdd = stack.pop();
                    stack.push(aAdd + bAdd);
                    break;

                case "-":
                    int bSub = stack.pop();
                    int aSub = stack.pop();
                    stack.push(aSub - bSub);
                    break;

                case "*":
                    int bMul = stack.pop();
                    int aMul = stack.pop();
                    stack.push(aMul * bMul);
                    break;

                default:
                    stack.push(Integer.parseInt(token));
                    break;
            }
        }

        System.out.println(stack.pop());
    }
}
