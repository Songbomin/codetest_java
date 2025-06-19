package step_11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Q7 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tokens = br.readLine().split(" ");
        int a1 = Integer.parseInt(tokens[0]);
        int a0 = Integer.parseInt(tokens[1]);

        int c = Integer.parseInt(br.readLine());

        int n0 = Integer.parseInt(br.readLine());

        // 조건 검사: a1 * n + a0 <= c * n
        // → a0 <= (c - a1) * n0
        if (a1 > c) {
            System.out.println(0);
        } else {
            if (a0 <= (c - a1) * n0) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}
