package setp01_21;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.StringBuilder;
import java.util.Arrays;

public class Q1 {

    // 빠른 입력을 위한 스캐너
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
        int M = fs.nextInt();
        int R = fs.nextInt();

        int[] U = new int[M];
        int[] V = new int[M];
        int[] deg = new int[N + 1];

        for (int i = 0; i < M; i++) {
            int a = fs.nextInt();
            int b = fs.nextInt();
            U[i] = a;
            V[i] = b;
            deg[a]++;
            deg[b]++;
        }

        int[][] adj = new int[N + 1][];
        for (int i = 1; i <= N; i++) {
            adj[i] = new int[deg[i]];
        }

        int[] pos = new int[N + 1];
        for (int i = 0; i < M; i++) {
            int a = U[i];
            int b = V[i];
            adj[a][pos[a]++] = b;
            adj[b][pos[b]++] = a;
        }

        for (int i = 1; i <= N; i++) {
            Arrays.sort(adj[i]);
        }

        int[] order = new int[N + 1];
        boolean[] visited = new boolean[N + 1];

        // 재귀 대신 스택(노드 + 다음에 볼 인접 인덱스)로 DFS 구현 (스택오버플로우 방지)
        int[] stackNode = new int[N];
        int[] stackIdx = new int[N];
        int sp = 0; // stack pointer (현재 스택 크기)

        visited[R] = true;
        order[R] = 1;

        stackNode[sp] = R;
        stackIdx[sp] = 0;
        sp++;

        int cnt = 1;

        while (sp > 0) {
            int cur = stackNode[sp - 1];
            int idx = stackIdx[sp - 1];

            if (idx == adj[cur].length) {
                sp--; // 더 볼 이웃이 없으면 되돌아감(재귀 return과 동일)
                continue;
            }

            int next = adj[cur][idx];
            stackIdx[sp - 1] = idx + 1;

            if (!visited[next]) {
                visited[next] = true;
                order[next] = ++cnt;

                stackNode[sp] = next;
                stackIdx[sp] = 0;
                sp++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(order[i]).append('\n');
        }
        System.out.print(sb.toString());
    }
}
