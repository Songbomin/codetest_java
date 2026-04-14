package step04_15;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Q1 {

    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class Result {
        int node;
        int distance;

        Result(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    static class FastScanner {
        private final BufferedInputStream in = new BufferedInputStream(System.in);
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0;
        private int len = 0;

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) {
                    return -1;
                }
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ');

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            int value = 0;
            while (c > ' ') {
                value = value * 10 + (c - '0');
                c = read();
            }
            return value * sign;
        }
    }

    static ArrayList<Edge>[] graph;

    static Result findFarthest(int start, int n) {
        boolean[] visited = new boolean[n + 1];
        int[] stackNode = new int[n];
        int[] stackDist = new int[n];
        int top = 0;

        stackNode[top] = start;
        stackDist[top] = 0;
        top++;
        visited[start] = true;

        int farthestNode = start;
        int farthestDist = 0;

        while (top > 0) {
            top--;
            int currentNode = stackNode[top];
            int currentDist = stackDist[top];

            if (currentDist > farthestDist) {
                farthestDist = currentDist;
                farthestNode = currentNode;
            }

            for (Edge edge : graph[currentNode]) {
                if (!visited[edge.to]) {
                    visited[edge.to] = true;
                    stackNode[top] = edge.to;
                    stackDist[top] = currentDist + edge.weight;
                    top++;
                }
            }
        }

        return new Result(farthestNode, farthestDist);
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        int n = fs.nextInt();

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        for (int i = 0; i < n - 1; i++) {
            int parent = fs.nextInt();
            int child = fs.nextInt();
            int weight = fs.nextInt();

            graph[parent].add(new Edge(child, weight));
            graph[child].add(new Edge(parent, weight));
        }

        if (n == 1) {
            System.out.println(0);
            return;
        }

        Result first = findFarthest(1, n);
        Result second = findFarthest(first.node, n);

        System.out.println(second.distance);
    }
}