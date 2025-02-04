package practice1.task3;

import java.util.*;

public class Task3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        sc.nextLine();
        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            board[i] = line.toCharArray();
        }
        solution(n, board);

        sc.close();
    }

    private static void solution(int n, char[][] board) {
        int ox1 = n, ox2 = -1, oy1 = n, oy2 = -1;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] == '#') {
                    int y = n - 1 - row;
                    ox1 = Math.min(ox1, col);
                    ox2 = Math.max(ox2, col);
                    oy1 = Math.min(oy1, y);
                    oy2 = Math.max(oy2, y);
                }
            }
        }
        if (ox2 == -1) {
            System.out.println("X");
            return;
        }

        int width = ox2 - ox1 + 1;
        int height = oy2 - oy1 + 1;

        boolean[][] used = new boolean[height][width];

        class Box {
            int minX, maxX, minY, maxY;

            Box(int minX, int maxX, int minY, int maxY) {
                this.minX = minX;
                this.maxX = maxX;
                this.minY = minY;
                this.maxY = maxY;
            }
        }
        List<Box> comps = new ArrayList<>();

        java.util.function.BiFunction<Integer, Integer, Character> getCell = (x, y) -> board[n - 1 - y][x];

        for (int y = oy1; y <= oy2; y++) {
            for (int x = ox1; x <= ox2; x++) {
                if (getCell.apply(x, y) == '.' && !used[y - oy1][x - ox1]) {
                    int minX = x, maxX = x, minY = y, maxY = y;
                    Deque<int[]> stack = new ArrayDeque<>();
                    stack.push(new int[]{x, y});
                    used[y - oy1][x - ox1] = true;
                    while (!stack.isEmpty()) {
                        int[] cur = stack.pop();
                        int cx = cur[0], cy = cur[1];
                        minX = Math.min(minX, cx);
                        maxX = Math.max(maxX, cx);
                        minY = Math.min(minY, cy);
                        maxY = Math.max(maxY, cy);
                        int[][] dirs = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
                        for (int[] d : dirs) {
                            int nx = cx + d[0], ny = cy + d[1];
                            if (nx >= ox1 && nx <= ox2 && ny >= oy1 && ny <= oy2) {
                                if (getCell.apply(nx, ny) == '.' && !used[ny - oy1][nx - ox1]) {
                                    used[ny - oy1][nx - ox1] = true;
                                    stack.push(new int[]{nx, ny});
                                }
                            }
                        }
                    }
                    comps.add(new Box(minX, maxX, minY, maxY));
                }
            }
        }
        int k = comps.size();

        boolean allComponentsRectangular = true;
        for (Box comp : comps) {
            for (int y = comp.minY; y <= comp.maxY; y++) {
                for (int x = comp.minX; x <= comp.maxX; x++) {
                    if (getCell.apply(x, y) != '.') {
                        allComponentsRectangular = false;
                        break;
                    }
                }
                if (!allComponentsRectangular) break;
            }

            if (!allComponentsRectangular) break;
        }
        if (!allComponentsRectangular) {
            System.out.println("X");
            return;
        }

        String ans = "X";
        if (k == 0) {
            ans = "I";
        }
        else if (k == 1) {
            Box comp = comps.getFirst();
            if (ox1 < comp.minX && comp.maxX < ox2 && oy1 < comp.minY && comp.maxY < oy2) {
                ans = "O";
            }
            else if (ox1 < comp.minX && comp.maxX == ox2 && oy1 < comp.minY && comp.maxY < oy2) {
                ans = "C";
            }

            else if (ox1 < comp.minX && comp.maxX == ox2 && comp.maxY == oy2 && oy1 < comp.minY) {
                ans = "L";
            }
        }
        else if (k == 2) {
            Box compA = comps.get(0), compB = comps.get(1);
            Box lower = null, upper = null;
            if (compA.minY == oy1) lower = compA;
            if (compB.minY == oy1) lower = compB;
            if (compA.maxY == oy2) upper = compA;
            if (compB.maxY == oy2) upper = compB;

            if (lower != null && upper != null) {
                if (lower.minX == upper.minX && lower.maxX == upper.maxX && lower.maxY < upper.minY) {
                    ans = "H";
                }
            }
            Box bottomC = null, topC = null;
            for (Box comp : comps) {
                if (comp.minY == oy1 && comp.maxX == ox2)
                    bottomC = comp;
                else
                    topC = comp;
            }
            if (bottomC != null && topC != null) {
                if (bottomC.minX > ox1 &&
                        topC.minX == bottomC.minX &&
                        topC.maxX < ox2 &&
                        bottomC.maxY < topC.minY &&
                        topC.maxY < oy2)
                    ans = "P";
            }
        }
        System.out.println(ans);
    }
}

