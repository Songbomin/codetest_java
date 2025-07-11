package step07_11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Q2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        int[][] apt = new int[15][15];

        for (int i = 1; i <= 14; i++) {
            apt[0][i] = i;
        }

        for (int k = 1; k <= 14; k++) {
            for (int n = 1; n <= 14; n++) {
                apt[k][n] = apt[k][n - 1] + apt[k - 1][n];
            }
        }

        for (int t = 0; t < T; t++) {
            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());
            System.out.println(apt[k][n]);
        }
    }
}
