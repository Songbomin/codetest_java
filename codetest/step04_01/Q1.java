package step04_01;

import java.io.BufferedInputStream;
import java.io.IOException;

public class Q1 {

    private static final int MOD = 1_000_000_007;

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

    static class FenwickTree {
        private final int size;
        private final int[] tree;

        FenwickTree(int size) {
            this.size = size;
            this.tree = new int[size + 1];
        }

        void add(int index, int value) {
            while (index <= size) {
                tree[index] += value;
                if (tree[index] >= MOD) {
                    tree[index] -= MOD;
                }
                index += index & -index;
            }
        }

        int sum(int index) {
            int result = 0;
            while (index > 0) {
                result += tree[index];
                if (result >= MOD) {
                    result -= MOD;
                }
                index -= index & -index;
            }
            return result;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int n = fs.nextInt();
        int k = fs.nextInt();

        FenwickTree[] trees = new FenwickTree[k + 1];
        for (int len = 1; len <= k; len++) {
            trees[len] = new FenwickTree(n);
        }

        int[] ways = new int[k + 1];

        for (int i = 0; i < n; i++) {
            int x = fs.nextInt();

            for (int len = k; len >= 2; len--) {
                ways[len] = trees[len - 1].sum(x - 1);
            }

            trees[1].add(x, 1);

            for (int len = 2; len <= k; len++) {
                if (ways[len] != 0) {
                    trees[len].add(x, ways[len]);
                }
            }
        }

        System.out.println(trees[k].sum(n));
    }
}
