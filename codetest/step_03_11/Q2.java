package step_03_11;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.lang.StringBuilder;
import java.util.HashSet;

public class Q2 {

    static int getCapacity(int size) {
        return (int) (size / 0.75f) + 1;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int n = fs.nextInt();
        int q = fs.nextInt();

        @SuppressWarnings("unchecked")
        HashSet<Integer>[] sets = new HashSet[n + 1];

        for (int i = 1; i <= n; i++) {
            int size = fs.nextInt();
            sets[i] = new HashSet<>(getCapacity(size));

            for (int j = 0; j < size; j++) {
                sets[i].add(fs.nextInt());
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < q; i++) {
            int type = fs.nextInt();

            if (type == 1) {
                int a = fs.nextInt();
                int b = fs.nextInt();

                if (sets[a].size() < sets[b].size()) {
                    HashSet<Integer> temp = sets[a];
                    sets[a] = sets[b];
                    sets[b] = temp;
                }

                sets[a].addAll(sets[b]);
                sets[b].clear();
            } else {
                int a = fs.nextInt();
                sb.append(sets[a].size()).append('\n');
            }
        }

        System.out.print(sb);
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
}