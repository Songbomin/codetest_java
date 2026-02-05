package step02_05;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.lang.Exception;
import java.lang.System;

public class Q2
{

    static int[] parent;
    static byte[] rank;

    static int find(int x) {
        int root = x;

        while (parent[root] != root) {
            root = parent[root];
        }

        while (parent[x] != x) {
            int next = parent[x];
            parent[x] = root;
            x = next;
        }

        return root;
    }

    static void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);

        if (ra == rb) {
            return;
        }

        if (rank[ra] < rank[rb]) {
            parent[ra] = rb;
        } else if (rank[ra] > rank[rb]) {
            parent[rb] = ra;
        } else {
            parent[rb] = ra;
            rank[ra]++;
        }
    }

    static final class FastScanner {
        private final BufferedInputStream in = new BufferedInputStream(System.in);
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0;
        private int len = 0;

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = readByte();
            } while (c <= 32);

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            int val = 0;
            while (c > 32) {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int n = fs.nextInt();
        int m = fs.nextInt();

        parent = new int[n + 1];
        rank = new byte[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int connected = fs.nextInt();
                if (connected == 1 && i < j) {
                    union(i, j);
                }
            }
        }

        int firstCity = fs.nextInt();
        int root = find(firstCity);

        boolean ok = true;

        for (int i = 1; i < m; i++) {
            int city = fs.nextInt();
            if (find(city) != root) {
                ok = false;
                break;
            }
        }

        System.out.print(ok ? "YES" : "NO");
    }
}
