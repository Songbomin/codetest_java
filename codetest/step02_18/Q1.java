package step02_18;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.lang.Math;
import java.lang.StringBuilder;

public class Q1 {

    // --------- Fast Scanner ----------
    private static final class FastScanner {
        private final BufferedInputStream in = new BufferedInputStream(System.in);
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

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

    // --------- Sieve ----------
    private static boolean[] sieve(int n) {
        boolean[] isPrime = new boolean[n + 1];
        if (n >= 2) {
            for (int i = 2; i <= n; i++) isPrime[i] = true;
            for (int p = 2; (long) p * p <= n; p++) {
                if (!isPrime[p]) continue;
                for (int m = p * p; m <= n; m += p) isPrime[m] = false;
            }
        }
        return isPrime;
    }

    // --------- FFT ----------
    private static void fft(double[] re, double[] im, boolean invert) {
        int n = re.length;

        // bit-reversal permutation
        for (int i = 1, j = 0; i < n; i++) {
            int bit = n >> 1;
            while ((j & bit) != 0) {
                j ^= bit;
                bit >>= 1;
            }
            j ^= bit;

            if (i < j) {
                double tmpRe = re[i]; re[i] = re[j]; re[j] = tmpRe;
                double tmpIm = im[i]; im[i] = im[j]; im[j] = tmpIm;
            }
        }

        for (int len = 2; len <= n; len <<= 1) {
            double ang = 2.0 * Math.PI / len * (invert ? -1.0 : 1.0);
            double wLenRe = Math.cos(ang);
            double wLenIm = Math.sin(ang);

            for (int i = 0; i < n; i += len) {
                double wRe = 1.0;
                double wIm = 0.0;

                int half = len >> 1;
                for (int j = 0; j < half; j++) {
                    int u = i + j;
                    int v = i + j + half;

                    double vRe = re[v] * wRe - im[v] * wIm;
                    double vIm = re[v] * wIm + im[v] * wRe;

                    double uRe = re[u];
                    double uIm = im[u];

                    re[u] = uRe + vRe;
                    im[u] = uIm + vIm;
                    re[v] = uRe - vRe;
                    im[v] = uIm - vIm;

                    double nextWRe = wRe * wLenRe - wIm * wLenIm;
                    double nextWIm = wRe * wLenIm + wIm * wLenRe;
                    wRe = nextWRe;
                    wIm = nextWIm;
                }
            }
        }

        if (invert) {
            for (int i = 0; i < n; i++) {
                re[i] /= n;
                im[i] /= n;
            }
        }
    }

    private static int nextPow2(int x) {
        int n = 1;
        while (n < x) n <<= 1;
        return n;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int T = fs.nextInt();
        int[] queries = new int[T];
        int maxN = 0;
        for (int i = 0; i < T; i++) {
            int n = fs.nextInt();
            queries[i] = n;
            if (n > maxN) maxN = n;
        }

        boolean[] isPrime = sieve(maxN);

        int size = nextPow2(maxN + 1 + maxN + 1); // 안전하게 2*maxN+2 이상
        double[] aRe = new double[size];
        double[] aIm = new double[size];
        double[] bRe = new double[size];
        double[] bIm = new double[size];

        // A[x] = 1 if x is odd prime
        for (int i = 3; i <= maxN; i += 2) {
            if (isPrime[i]) aRe[i] = 1.0;
        }

        // B[y] = 1 if y = 2 * p where p is prime (even semiprime)
        for (int p = 2; p * 2 <= maxN; p++) {
            if (isPrime[p]) bRe[p * 2] = 1.0;
        }

        fft(aRe, aIm, false);
        fft(bRe, bIm, false);

        // point-wise multiply
        for (int i = 0; i < size; i++) {
            double re = aRe[i] * bRe[i] - aIm[i] * bIm[i];
            double im = aRe[i] * bIm[i] + aIm[i] * bRe[i];
            aRe[i] = re;
            aIm[i] = im;
        }

        fft(aRe, aIm, true); // inverse FFT -> aRe holds convolution results

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int n = queries[i];
            long ans = Math.round(aRe[n]); // 오차 보정
            sb.append(ans).append('\n');
        }
        System.out.print(sb.toString());
    }
}

