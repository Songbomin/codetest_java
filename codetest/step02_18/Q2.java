package step02_18;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.lang.Math;
import java.lang.StringBuilder;

public class Q2 {

    // -------------------- Fast Input --------------------
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

    // -------------------- FFT --------------------
    private static void fft(double[] re, double[] im, boolean invert) {
        int n = re.length;

        // (1) bit-reversal permutation
        for (int i = 1, j = 0; i < n; i++) {
            int bit = n >> 1;
            while ((j & bit) != 0) {
                j ^= bit;
                bit >>= 1;
            }
            j ^= bit;

            if (i < j) {
                double tr = re[i]; re[i] = re[j]; re[j] = tr;
                double ti = im[i]; im[i] = im[j]; im[j] = ti;
            }
        }

        // (2) iterative Cooley-Tukey
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

        // (3) normalize for inverse FFT
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

        int N = fs.nextInt();
        int[] X = new int[N];
        int[] Y = new int[N];

        for (int i = 0; i < N; i++) X[i] = fs.nextInt();
        for (int i = 0; i < N; i++) Y[i] = fs.nextInt();

        // A = X concatenated with X (length 2N)
        // B = reverse(Y) (length N)
        int need = (2 * N) + N - 1;          // convolution length
        int size = nextPow2(need + 1);       // FFT size (power of 2)

        double[] aRe = new double[size];
        double[] aIm = new double[size];
        double[] bRe = new double[size];
        double[] bIm = new double[size];

        for (int i = 0; i < N; i++) {
            aRe[i] = X[i];
            aRe[i + N] = X[i];
        }

        for (int i = 0; i < N; i++) {
            bRe[i] = Y[N - 1 - i];
        }

        // FFT
        fft(aRe, aIm, false);
        fft(bRe, bIm, false);

        // point-wise multiplication
        for (int i = 0; i < size; i++) {
            double re = aRe[i] * bRe[i] - aIm[i] * bIm[i];
            double im = aRe[i] * bIm[i] + aIm[i] * bRe[i];
            aRe[i] = re;
            aIm[i] = im;
        }

        // inverse FFT -> aRe holds convolution
        fft(aRe, aIm, true);

        long best = 0L;
        int base = N - 1;
        for (int k = 0; k < N; k++) {
            long val = Math.round(aRe[base + k]);
            if (val > best) best = val;
        }

        System.out.print(best);
    }
}

