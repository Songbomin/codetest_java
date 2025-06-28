package step_15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Q5 {
    private static boolean isPrime(long n) {
        if (n < 2) return false;
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            long n = Long.parseLong(br.readLine());

            while (!isPrime(n)) {
                n++;
            }

            sb.append(n).append("\n");
        }

        // 출력
        System.out.print(sb);
    }
}
