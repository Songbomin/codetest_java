package setp01_24;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;

public class Q1 {

    // 빠른 입력을 위한 커스텀 스캐너
    private static class FastScanner {
        private final BufferedInputStream in = new BufferedInputStream(System.in);
        private final byte[] buffer = new byte[1 << 16]; // 65536 bytes
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
        FastScanner fs = new FastScanner();

        int n = fs.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = fs.nextInt();
        }

        Arrays.sort(arr);

        int left = 0;
        int right = n - 1;

        long bestAbs = Long.MAX_VALUE;
        int bestA = arr[left];
        int bestB = arr[right];

        while (left < right) {
            long sum = (long) arr[left] + arr[right];
            long abs = sum >= 0 ? sum : -sum;

            if (abs < bestAbs) {
                bestAbs = abs;
                bestA = arr[left];
                bestB = arr[right];
                if (bestAbs == 0) break;
            }

            if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }

        System.out.print(bestA + " " + bestB);
    }
}

