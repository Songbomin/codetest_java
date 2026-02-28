package setp02_28;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class Q1 {

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

    private static class Point {
        long x;
        long y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    private static long cross(Point a, Point b, Point c) {
        return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int n = (int) fs.nextLong();
        Point[] pts = new Point[n];

        for (int i = 0; i < n; i++) {
            long x = fs.nextLong();
            long y = fs.nextLong();
            pts[i] = new Point(x, y);
        }

        Arrays.sort(pts, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                if (p1.x != p2.x) return Long.compare(p1.x, p2.x);
                return Long.compare(p1.y, p2.y);
            }
        });

        Point[] hull = new Point[2 * n];
        int m = 0;

        for (int i = 0; i < n; i++) {
            Point cur = pts[i];
            while (m >= 2 && cross(hull[m - 2], hull[m - 1], cur) <= 0) {
                m--;
            }
            hull[m++] = cur;
        }

        int t = m + 1;

        for (int i = n - 2; i >= 0; i--) {
            Point cur = pts[i];
            while (m >= t && cross(hull[m - 2], hull[m - 1], cur) <= 0) {
                m--;
            }
            hull[m++] = cur;
        }

        m--;

        System.out.print(m);
    }
}
