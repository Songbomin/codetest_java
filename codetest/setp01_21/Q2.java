package setp01_21;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.StringBuilder;

public class Q2 {

    // 빠른 입력
    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0;
        private int len = 0;

        FastScanner(InputStream in) {
            this.in = in;
        }

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
            } while (c <= ' ');

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(new BufferedInputStream(System.in));

        int N = fs.nextInt();
        int[] r = new int[N + 1];
        int[] c = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            r[i] = fs.nextInt();
            c[i] = fs.nextInt();
        }

        long[][] dp = new long[N + 1][N + 1];
        long INF = Long.MAX_VALUE / 4;

        for (int len = 2; len <= N; len++) {
            for (int i = 1; i + len - 1 <= N; i++) {
                int j = i + len - 1;
                long best = INF;

                long ri = r[i];
                long cj = c[j];

                for (int k = i; k < j; k++) {
                    long cost = dp[i][k] + dp[k + 1][j] + ri * (long) c[k] * cj;
                    if (cost < best) best = cost;
                }

                dp[i][j] = best;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dp[1][N]);
        System.out.print(sb.toString());
    }
}

