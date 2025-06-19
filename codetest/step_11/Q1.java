package step_11;

import java.io.IOException;

public class Q1 {
    public static void main(String[] args) throws IOException {
        int n = readInt();
        System.out.println(1);
        System.out.println(0);
    }

    private static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) >= '0') {
            n = n * 10 + (c - '0');
        }
        return n;
    }
}
