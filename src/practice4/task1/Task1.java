package practice4.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Task1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Map<String, String> parent = new HashMap<>();
        Set<String> people = new HashSet<>();

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String child = st.nextToken();
            String par = st.nextToken();

            parent.put(child, par);
            people.add(child);
            people.add(par);
        }

        solution(parent, people);

        br.close();
    }

    private static void solution(Map<String, String> parent, Set<String> people) {
        Map<String, Integer> h = new HashMap<>();
        for (String person : people) {
            getHeight(person, parent, h);
        }

        List<String> sorted = new ArrayList<>(people);
        Collections.sort(sorted);

        PrintWriter out = new PrintWriter(System.out);

        for (String p : sorted) {
            out.println(p + " " + h.get(p));
            out.flush();
        }
    }

    private static int getHeight(String person, Map<String, String> parent, Map<String, Integer> h) {
        if (h.containsKey(person)) return h.get(person);

        if (!parent.containsKey(person)) {
            h.put(person, 0);
            return 0;
        }

        int height = getHeight(parent.get(person), parent, h) + 1;
        h.put(person, height);
        return height;
    }
}
