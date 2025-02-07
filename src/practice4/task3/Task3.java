package practice4.task3;

import java.io.*;
import java.util.*;

public class Task3 {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/practice4/task3/input.txt"));

            int n = Integer.parseInt(reader.readLine().trim());

            Map<String, String> parents = new HashMap<>();

            for (int i = 0; i < n - 1; i++) {
                String[] line = reader.readLine().strip().split(" ");
                String child = line[0];
                String parent = line[1];
                parents.put(child, parent);
            }

            String queryLine;
            while ((queryLine = reader.readLine()) != null) {
                String[] names = queryLine.strip().split(" ");
                String name1 = names[0];
                String name2 = names[1];

                Set<String> ancestors = new HashSet<>();
                ancestors.add(name1);

                while (parents.containsKey(name1)) {
                    name1 = parents.get(name1);
                    ancestors.add(name1);
                }

                while (!ancestors.contains(name2)) {
                    name2 = parents.get(name2);
                }

                System.out.println(name2);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
