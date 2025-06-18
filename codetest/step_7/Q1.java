package step_7;

import java.io.IOException;

public class Q1 {
    public static void main(String[] args) throws IOException {
        int n = nextInt(), m = nextInt();
        int[][] result = new int[n][m];

        for (int t = 0; t < 2; t++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    result[i][j] += nextInt();
                }
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int[] row : result) {
            for (int val : row) {
                sb.append(val).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    private static int nextInt() throws IOException {
        int value = 0;
        int c;

        while ((c = System.in.read()) > 32) {
            value = value * 10 + (c - '0'); // 예: '3'은 51이므로 51 - 48 = 3
        }

        return value;
    }
}

