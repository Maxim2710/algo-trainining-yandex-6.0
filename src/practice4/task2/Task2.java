package practice4.task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Task2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Map<String, List<String>> children = new HashMap<>();
        Set<String> people = new HashSet<>();

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String child = st.nextToken();
            String par = st.nextToken();

            children.computeIfAbsent(par, k -> new ArrayList<>()).add(child);
            people.add(child);
            people.add(par);
        }

        solution(children, people);

        br.close();
    }

    private static void solution(Map<String, List<String>> children, Set<String> people) {
        Map<String, Integer> cntP = new HashMap<>();

        for (String person : people) {
            getDescendants(person, children, cntP);
        }

        PrintWriter out = new PrintWriter(System.out);

        List<String> sorted = new ArrayList<>(people);
        Collections.sort(sorted);

        for(String p : sorted) {
            out.println(p + " " + cntP.get(p));
        }

        out.flush();
    }

    private static int getDescendants(String person, Map<String, List<String>> children, Map<String, Integer> cntP) {
        if (cntP.containsKey(person)) return cntP.get(person);

        int cnt = 0;
        if (children.containsKey(person)) {
            for (String child : children.get(person)) {
                cnt += 1 + getDescendants(child, children, cntP);
            }
        }

        cntP.put(person, cnt);

        return cnt;
    }


}

