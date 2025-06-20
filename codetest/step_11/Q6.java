package step_11;

import java.io.*;

public class Q6 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long count = (long)n * (n - 1) * (n - 2) / 6;

        System.out.println(count);
        System.out.println(3);
    }
}
