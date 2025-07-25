package step07_24;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Q1 {
    static boolean[][] board;
    static int min = 64; // 최대 바꿔야 하는 칸 수는 8*8 = 64칸

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] size = br.readLine().split(" ");
        int N = Integer.parseInt(size[0]);
        int M = Integer.parseInt(size[1]);

        board = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j) == 'B'; // B면 true, W면 false
            }
        }

        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                check(i, j);
            }
        }

        System.out.println(min);
    }

    public static void check(int x, int y) {
        int endX = x + 8;
        int endY = y + 8;

        boolean first = board[x][y]; // 기준 색(B or W)
        int count = 0;

        for (int i = x; i < endX; i++) {
            for (int j = y; j < endY; j++) {
                if (board[i][j] != first) {
                    count++;
                }
                first = !first; // 다음 칸은 색 반대
            }
            first = !first; // 줄 바뀔 때도 반대 색으로 시작해야 함
        }

        int reverse = 64 - count; // 반대 색으로 시작하는 경우도 고려
        min = Math.min(min, Math.min(count, reverse));
    }
}

