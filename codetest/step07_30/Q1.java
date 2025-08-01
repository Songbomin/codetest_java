package step07_30;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Q1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int t = 0; t < 3; t++) {
            int n = Integer.parseInt(br.readLine());
            long sum = 0;

            for (int i = 0; i < n; i++) {
                long num = Long.parseLong(br.readLine());
                sum += num;
            }

            if (sum > 0) {
                System.out.println("+");
            } else if (sum < 0) {
                System.out.println("-");
            } else {
                System.out.println("0");
            }
        }
    }
}

