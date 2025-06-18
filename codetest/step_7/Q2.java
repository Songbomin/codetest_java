package step_7;

import java.io.IOException;

public class Q2 {
    public static void main(String[] args) throws IOException {
        int max = -1;
        int row = 0, col = 0;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int num = nextInt();
                if (num > max) {
                    max = num;
                    row = i;
                    col = j;
                }
            }
        }

        System.out.println(max);
        System.out.println((row + 1) + " " + (col + 1));
    }

    private static int nextInt() throws IOException {
        int val = 0;
        int c;
        while ((c = System.in.read()) > 32) {
            val = val * 10 + (c - '0');
        }
        return val;
    }
}

