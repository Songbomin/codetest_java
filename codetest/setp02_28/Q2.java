package setp02_28;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class Q2 {

    // 빠른 입력
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

        long nextLong() throws IOException {
            int c;
            do {
                c = readByte();
            } while (c <= ' ');

            long sign = 1;
            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }
    }

    private static class Star {
        long x;
        long y;
        long s;
        long dist2;
        int angleIdx;

        Star(long x, long y, long s) {
            this.x = x;
            this.y = y;
            this.s = s;
            this.dist2 = x * x + y * y;
        }
    }

    // 위쪽 반평면(0) / 아래쪽 반평면(1) 구분 (각도 정렬용)
    private static int half(long x, long y) {
        if (y > 0) return 0;
        if (y == 0 && x > 0) return 0;
        return 1;
    }

    // a->b가 0~90도 안쪽(반시계)인지 체크: cross >= 0 && dot >= 0
    private static boolean within90(long ax, long ay, long bx, long by) {
        long cross = ax * by - ay * bx;
        if (cross < 0) return false;
        long dot = ax * bx + ay * by;
        return dot >= 0;
    }

    // 현재 활성화된 가중치(weight2)를 기준으로, 90도 섹터 최대 아름다움 합 구하기
    private static long computeMaxSum90(long[] x2, long[] y2, long[] w2, int n) {
        long best = 0;
        long sum = 0;
        int j = 0;

        for (int i = 0; i < n; i++) {
            if (j < i) { // 안전장치 (원칙상 j는 항상 i 이상)
                j = i;
                sum = 0;
            }

            while (j < i + n && within90(x2[i], y2[i], x2[j], y2[j])) {
                sum += w2[j];
                j++;
            }

            if (sum > best) best = sum;

            sum -= w2[i];
        }

        return best;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int n = (int) fs.nextLong();
        int m = (int) fs.nextLong();

        Star[] stars = new Star[n];
        for (int i = 0; i < n; i++) {
            long x = fs.nextLong();
            long y = fs.nextLong();
            long s = fs.nextLong();
            stars[i] = new Star(x, y, s);
        }

        long[] prices = new long[m];
        for (int i = 0; i < m; i++) {
            prices[i] = fs.nextLong();
        }
        Arrays.sort(prices);

        // 1) 각도 정렬
        Arrays.sort(stars, new Comparator<Star>() {
            @Override
            public int compare(Star a, Star b) {
                int ha = half(a.x, a.y);
                int hb = half(b.x, b.y);
                if (ha != hb) return ha - hb;

                long cross = a.x * b.y - a.y * b.x;
                if (cross != 0) return cross > 0 ? -1 : 1;

                // 같은 방향(일직선)이면 가까운 순으로 (큰 의미는 없지만 안정적으로)
                return Long.compare(a.dist2, b.dist2);
            }
        });

        // 각도 배열 생성 + angleIdx 기록
        long[] x2 = new long[2 * n];
        long[] y2 = new long[2 * n];
        for (int i = 0; i < n; i++) {
            stars[i].angleIdx = i;
            x2[i] = stars[i].x;
            y2[i] = stars[i].y;
            x2[i + n] = stars[i].x;
            y2[i + n] = stars[i].y;
        }

        // 2) 거리 정렬용 배열
        Star[] byDist = stars.clone();
        Arrays.sort(byDist, new Comparator<Star>() {
            @Override
            public int compare(Star a, Star b) {
                return Long.compare(a.dist2, b.dist2);
            }
        });

        // 활성화 가중치(각도 배열 2배)
        long[] w2 = new long[2 * n];

        int ptr = 0; // byDist에서 활성화할 별 포인터
        long answer = Long.MIN_VALUE;

        for (int i = 0; i < m; i++) {
            long P = prices[i];

            // dist2 <= P 인 별들 활성화
            while (ptr < n && byDist[ptr].dist2 <= P) {
                int idx = byDist[ptr].angleIdx;
                long val = byDist[ptr].s;
                w2[idx] = val;
                w2[idx + n] = val;
                ptr++;
            }

            long maxBeauty = computeMaxSum90(x2, y2, w2, n);
            long candidate = maxBeauty - P;
            if (candidate > answer) answer = candidate;
        }

        System.out.print(answer);
    }
}
