package practice1.task1;

import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int x1 = scanner.nextInt();
        int y1 = scanner.nextInt();

        int x2 = scanner.nextInt();
        int y2 = scanner.nextInt();

        int x = scanner.nextInt();
        int y = scanner.nextInt();

        Task1.Solution(x1, y1, x2, y2, x, y);
    }

    private static void Solution(int x1, int y1, int x2, int y2, int x, int y) {

        if (x1 < x && x < x2) {
            if (y > y2) System.out.println("N");
            else System.out.println("S");
        } else if (y1 < y && y < y2){
            if (x < x1) System.out.println("W");
            else System.out.println("E");
        } else {
            if (x1 > x && y > y2) System.out.println("NW");
            else if (x1 > x && y < y1) System.out.println("SW");
            else if (y1 > y && x2 < x) System.out.println("SE");
            else System.out.println("NE");
        }

    }
}
