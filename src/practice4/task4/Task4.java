package practice4.task4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Task4 {
    static class Node {
        int key;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
        }
    }

    static Node root = null;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty())
                continue;
            String[] parts = line.split("\\s+");
            String command = parts[0];
            if ("ADD".equals(command)) {
                int num = Integer.parseInt(parts[1]);
                if (search(root, num) != null) {
                    out.append("ALREADY\n");
                } else {
                    root = insert(root, num);
                    out.append("DONE\n");
                }
            } else if ("SEARCH".equals(command)) {
                int num = Integer.parseInt(parts[1]);
                out.append(search(root, num) != null ? "YES\n" : "NO\n");
            } else if ("PRINTTREE".equals(command)) {
                printTree(root, 0, out);
            }
        }
        System.out.print(out);
    }

    static Node insert(Node node, int key) {
        if (node == null)
            return new Node(key);
        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        }
        return node;
    }

    static Node search(Node node, int key) {
        if (node == null)
            return null;
        if (node.key == key)
            return node;
        return key < node.key ? search(node.left, key) : search(node.right, key);
    }

    static void printTree(Node node, int depth, StringBuilder out) {
        if (node == null)
            return;
        printTree(node.left, depth + 1, out);
        for (int i = 0; i < depth; i++) {
            out.append(".");
        }
        out.append(node.key).append("\n");
        printTree(node.right, depth + 1, out);
    }
}

