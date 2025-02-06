package practice3.task5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Task5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        if (line == null) {
            System.out.println("WRONG");
            return;
        }

        List<String> tokenize = tokenize(line);
        if (tokenize == null) {
            System.out.println("WRONG");
            return;
        }

        List<String> postfix = infixToPostfix(tokenize);
        if (postfix == null) {
            System.out.println("WRONG");
            return;
        }

        long result = evalPostfix(postfix);

        System.out.println(result);

        br.close();
    }

    private static List<String> tokenize(String line) {
        List<String> resultTokenize = new ArrayList<>();
        int i = 0;

        while (i < line.length()) {
            char c = line.charAt(i);

            if (Character.isWhitespace(c)) {
                i++;
            } else if (Character.isDigit(c)) {
                int j = i;

                while (j < line.length() && Character.isDigit(line.charAt(j))) {
                    j++;
                }

                resultTokenize.add(line.substring(i, j));
                i = j;
            } else if (c == '+' || c == '-' || c == '(' || c == ')' || c == '*') {
                resultTokenize.add(String.valueOf(c));
                i++;
            } else {
                return null;
            }
        }

        return resultTokenize;
    }

    private static List<String> infixToPostfix(List<String> tokenize) {
        List<String> output = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        String checkedType = "op";

        for (String token : tokenize) {
            if (token.matches("\\d+")) {
                if (checkedType.equals("num")) {
                    return null;
                }

                output.add(token);
                checkedType = "num";
            } else if (token.equals("(")) {
                if (checkedType.equals("num")) {
                    return null;
                }

                stack.push(token);
                checkedType = "op";
            } else if (token.equals(")")) {
                if (checkedType.equals("op")) {
                    return null;
                }

                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    output.add(stack.pop());
                }

                if (stack.isEmpty()) {
                    return null;
                }

                stack.pop();
                checkedType = "num";
            } else if (isOperator(token)) {
                if (checkedType.equals("op")) {
                    return null;
                }

                while (!stack.isEmpty() && isOperator(stack.peek()) && precedence(stack.peek()) >= precedence(token)) {
                    output.add(stack.pop());
                }

                stack.push(token);
                checkedType = "op";
            } else {
                return null;
            }
        }

        if (checkedType.equals("op")) return null;

        while (!stack.isEmpty()) {
            if (stack.peek().equals("(")) return null;

            output.add(stack.pop());
        }

        return output;
    }

    private static long evalPostfix(List<String> postfix) {
        Deque<Long> deque = new ArrayDeque<>();
        for (String s : postfix) {
            if (s.matches("\\d+")) {
                deque.push(Long.parseLong(s));
            } else if (isOperator(s)) {
                if (deque.size() < 2) {
                    return -1;
                }

                long b = deque.pop();
                long a = deque.pop();

                switch (s) {
                    case "*":
                        deque.push(a * b);
                        break;

                    case "-":
                        deque.push(a - b);
                        break;

                    case "+":
                        deque.push(a + b);
                        break;
                }
            }
        }

        return deque.pop();
    }

    private static boolean isOperator(String s) {
        return s.equals("*") || s.equals("-") || s.equals("+");
    }

    private static int precedence(String op) {
        if (op.equals("*")) return 2;
        return 1;
    }
}

// Предварительно добавим проверки:
// 1. Если строка null - ОШИБКА
// 2. Если токенизация вернула null - ОШИБКА
// 3. infixToPostfix вернула null - ОШИБКА


// tokenize
// 1. Если пробел - увеличиваем индекс и переходим к следующему
// 2. Если это число - запоминаем начальный индекс и идем по числу (например, для числа 1134) - запихиваем в результат
// 3. Если это ( ) * - +, тогда запихиваем это в результат
// 4. Недопустимый символ - возвращаем null, это будет ошибкой


// infixToPostfix
// output - префиксный порядок токенов
// stack - текущий стек для операторов

// 1. Создаем переменную "op", которая будет ожидать сейчас либо открывающую скобку, либо число
// 2. Если текущий символ - это число - смотрим, не было ли числа до этого - "num" в переменной
// 3. Если открывающаяся скобка - не было ли перед этим числа - "num" в переменной
// 4. Если вылезла закрывающая скобка - проверяем, что до этого не было открывающей - "op" в переменной
// 5. Далее либо пока стек не кончится, либо пока не найдем открывающую скобку - извлекаем операторы из стека (не нашли открывающуюся скобку - ошибка)
// 6. Если токен - оператор и перед этим был найден оператор. Если на вершине стека оператор с бОльшим приоритетом или равным - выталкиваем его в результат
// 7. Если последнее, что прочитали - это оператор - ОШИБКА
// 8. Выталкиваем все остальное из стека (если нашли открывающую скобку - ОШИБКА)


// evalPostfix - берем метод с прошлого задания (где работали с постфиксным выражением

// isOperator - для проверки на оператор

// precedence - выявление приоритета операции
