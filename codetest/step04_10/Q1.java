package step04_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1 {
    static int N;
    static long C;
    static long[] weights;
    static long[] leftSums;
    static long[] rightSums;

    static void makeSums(int start, int end, long sum, long[] sums, int[] idx) {
        if (sum > C) {
            return;
        }

        if (start == end) {
            sums[idx[0]++] = sum;
            return;
        }

        makeSums(start + 1, end, sum, sums, idx);
        makeSums(start + 1, end, sum + weights[start], sums, idx);
    }

    static int upperBound(long[] arr, int size, long target) {
        int left = 0;
        int right = size;

        while (left < right) {
            int mid = (left + right) >>> 1;

            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader();

        N = fr.nextInt();
        C = fr.nextLong();

        weights = new long[N];
        for (int i = 0; i < N; i++) {
            weights[i] = fr.nextLong();
        }

        int mid = N / 2;

        leftSums = new long[1 << mid];
        rightSums = new long[1 << (N - mid)];

        int[] leftIdx = new int[1];
        int[] rightIdx = new int[1];

        makeSums(0, mid, 0L, leftSums, leftIdx);
        makeSums(mid, N, 0L, rightSums, rightIdx);

        Arrays.sort(rightSums, 0, rightIdx[0]);

        long answer = 0;

        for (int i = 0; i < leftIdx[0]; i++) {
            long remain = C - leftSums[i];
            answer += upperBound(rightSums, rightIdx[0], remain);
        }

        System.out.println(answer);
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }
    }
}
