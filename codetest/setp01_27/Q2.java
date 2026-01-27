package setp01_27;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2 {

    static int N;
    static int M;
    static int[] selected;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        selected = new int[M];

        dfs(0, 1);

        System.out.print(sb.toString());
    }

    static void dfs(int depth, int start) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(selected[i]);
                if (i + 1 < M) sb.append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int num = start; num <= N; num++) {
            selected[depth] = num;
            dfs(depth + 1, num + 1);
        }
    }
}

