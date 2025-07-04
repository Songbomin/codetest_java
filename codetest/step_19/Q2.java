package step_19;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Q2 {
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        map = new char[n][n];
        drawStar(0, 0, n, false);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(map[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    public static void drawStar(int x, int y, int size, boolean blank) {
        if (blank) {
            for (int i = x; i < x + size; i++) {
                for (int j = y; j < y + size; j++) {
                    map[i][j] = ' ';
                }
            }
            return;
        }

        if (size == 1) {
            map[x][y] = '*';
            return;
        }

        int newSize = size / 3;
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                count++;
                if (count == 5) {
                    drawStar(x + i * newSize, y + j * newSize, newSize, true); // 중앙 공백
                } else {
                    drawStar(x + i * newSize, y + j * newSize, newSize, false); // 나머지 8칸
                }
            }
        }
    }
}
