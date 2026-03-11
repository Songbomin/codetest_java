package step_03_11;

import java.io.InputStream;
import java.io.IOException;
import java.util.HashSet;

public class Q1 {

    static int[] originalParent;
    static int[] ufParent;
    static HashSet<Integer>[] colorSet;

    static int find(int x) {
        while (ufParent[x] != x) {
            ufParent[x] = ufParent[ufParent[x]];
            x = ufParent[x];
        }
        return x;
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) {
            return;
        }

        if (colorSet[rootA].size() < colorSet[rootB].size()) {
            int temp = rootA;
            rootA = rootB;
            rootB = temp;
        }

        ufParent[rootB] = rootA;

        for (int color : colorSet[rootB]) {
            colorSet[rootA].add(color);
        }

        colorSet[rootB] = null;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int q = fs.nextInt();

        originalParent = new int[n + 1];
        originalParent[1] = 0;

        for (int node = 2; node <= n; node++) {
            originalParent[node] = fs.nextInt();
        }

        int[] color = new int[n + 1];
        for (int node = 1; node <= n; node++) {
            color[node] = fs.nextInt();
        }

        int totalQueries = n + q - 1;
        int[] queryType = new int[totalQueries];
        int[] queryNode = new int[totalQueries];

        for (int i = 0; i < totalQueries; i++) {
            queryType[i] = fs.nextInt();
            queryNode[i] = fs.nextInt();
        }

        ufParent = new int[n + 1];
        colorSet = new HashSet[n + 1];

        for (int node = 1; node <= n; node++) {
            ufParent[node] = node;
            colorSet[node] = new HashSet<>(2);
            colorSet[node].add(color[node]);
        }

        int[] answers = new int[q];
        int answerIndex = 0;

        for (int i = totalQueries - 1; i >= 0; i--) {
            int type = queryType[i];
            int node = queryNode[i];

            if (type == 1) {
                union(node, originalParent[node]);
            } else {
                answers[answerIndex++] = colorSet[find(node)].size();
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = answerIndex - 1; i >= 0; i--) {
            sb.append(answers[i]).append('\n');
        }

        System.out.print(sb);
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0;
        private int len = 0;

        FastScanner(InputStream in) {
            this.in = in;
        }

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

            int value = 0;

            while (c > ' ') {
                value = value * 10 + (c - '0');
                c = read();
            }

            return value;
        }
    }
}
