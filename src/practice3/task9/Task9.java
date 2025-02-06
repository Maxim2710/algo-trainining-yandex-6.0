package practice3.task9;

import java.util.*;

public class Task9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int mainA = sc.nextInt(), mainB = sc.nextInt();

        boolean mainChanging = !((mainA % 2) == (mainB % 2));

        int[] d = new int[N], arrival = new int[N], passTime = new int[N];

        ArrayList<Integer>[] roads = new ArrayList[5];
        for (int i = 0; i < 5; i++) {
            roads[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            d[i] = sc.nextInt();
            arrival[i] = sc.nextInt();
            roads[d[i]].add(i);
        }

        for (int road = 1; road <= 4; road++) {
            roads[road].sort(Comparator.comparingInt(i -> arrival[i]));
        }

        int[] ptr = new int[5];
        int completed = 0;
        int curTime = 1;

        while (completed < N) {
            ArrayList<Integer> moveList = new ArrayList<>();

            for (int road = 1; road <= 4; road++) {
                if (!hasReady(roads, road, arrival, ptr, curTime)) {
                    continue;
                }
                int idx = roads[road].get(ptr[road]);

                if (!isMain(road, mainA, mainB) && existsReadyMain(roads, arrival, ptr, curTime, mainA, mainB)) {
                    continue;
                }

                if (isMain(road, mainA, mainB) && !mainChanging) {
                    moveList.add(idx);
                    continue;
                }

                int rightRoad = getRight(road);

                if (hasReady(roads, rightRoad, arrival, ptr, curTime)) {
                    if (isMain(road, mainA, mainB) && isMain(rightRoad, mainA, mainB)) {
                        continue;
                    }
                    if (!isMain(road, mainA, mainB)) {
                        continue;
                    }
                }
                moveList.add(idx);
            }

            if (moveList.isEmpty()) {
                curTime++;
                continue;
            }

            for (int idx : moveList) {
                passTime[idx] = curTime;
                int road = d[idx];
                ptr[road]++;
                completed++;
            }
            curTime++;
        }

        for (int i = 0; i < N; i++) {
            System.out.println(passTime[i]);
        }
    }

    static int getRight(int d) {
        return d == 1 ? 4 : d - 1;
    }

    static boolean isMain(int d, int mainA, int mainB) {
        return d == mainA || d == mainB;
    }

    static boolean hasReady(ArrayList<Integer>[] roads, int road, int[] arrival, int[] ptr, int curTime) {
        return ptr[road] < roads[road].size() && arrival[roads[road].get(ptr[road])] <= curTime;
    }

    static boolean existsReadyMain(ArrayList<Integer>[] roads, int[] arrival, int[] ptr, int curTime, int mainA, int mainB) {
        for (int road = 1; road <= 4; road++) {
            if (isMain(road, mainA, mainB) && hasReady(roads, road, arrival, ptr, curTime)) {
                return true;
            }
        }
        return false;
    }
}
