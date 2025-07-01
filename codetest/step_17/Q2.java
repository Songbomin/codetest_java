package step_17;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Q2 {
    static int[][] dp = new int[31][31];

    // 조합 계산 함수
    static int comb(int n, int r) {
        if (dp[n][r] > 0) return dp[n][r];
        if (n == r || r == 0) return dp[n][r] = 1;
        return dp[n][r] = comb(n - 1, r - 1) + comb(n - 1, r);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            sb.append(comb(M, N)).append('\n');
        }

        System.out.print(sb);
    }
}
