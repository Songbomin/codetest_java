package setp01_24;

import java.io.BufferedInputStream;
import java.io.IOException;

public class Q2 {

    // 빠른 입력 (Scanner 금지급: 느림)
    private static class FastScanner {
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
            } while (c <= ' ');

            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int n = fs.nextInt();
        int s = fs.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = fs.nextInt();
        }

        int left = 0;
        long sum = 0L;
        int best = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum >= s) {
                int len = right - left + 1;
                if (len < best) best = len;
                sum -= arr[left];
                left++;
            }
        }

        if (best == Integer.MAX_VALUE) {
            System.out.print(0);
        } else {
            System.out.print(best);
        }
    }
}

