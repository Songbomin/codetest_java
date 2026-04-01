package step04_01;

import java.io.BufferedInputStream;
import java.io.IOException;

public class Q2 {

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

        long nextLong() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ');

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            long value = 0;
            while (c > ' ') {
                value = value * 10 + (c - '0');
                c = read();
            }
            return value * sign;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }

    static class FenwickTree {
        private final int size;
        private final long[] tree;

        FenwickTree(int size) {
            this.size = size;
            this.tree = new long[size + 1];
        }

        void add(int index, long value) {
            while (index <= size) {
                tree[index] += value;
                index += index & -index;
            }
        }

        long sum(int index) {
            long result = 0;
            while (index > 0) {
                result += tree[index];
                index -= index & -index;
            }
            return result;
        }

        void rangeAdd(int left, int right, long value) {
            add(left, value);
            if (right + 1 <= size) {
                add(right + 1, -value);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int n = fs.nextInt();
        long[] base = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            base[i] = fs.nextLong();
        }

        int q = fs.nextInt();

        FenwickTree coefficientTree = new FenwickTree(n);
        FenwickTree constantTree = new FenwickTree(n);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < q; i++) {
            int type = fs.nextInt();

            if (type == 1) {
                int l = fs.nextInt();
                int r = fs.nextInt();

                coefficientTree.rangeAdd(l, r, 1L);
                constantTree.rangeAdd(l, r, 1L - l);
            } else {
                int x = fs.nextInt();

                long coefficientSum = coefficientTree.sum(x);
                long constantSum = constantTree.sum(x);
                long answer = base[x] + coefficientSum * x + constantSum;

                sb.append(answer).append('\n');
            }
        }

        System.out.print(sb);
    }
}
