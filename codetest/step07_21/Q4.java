package step07_21;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Q4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int count = 0;
        int num = 665;

        while (count < N) {
            num++;
            if (String.valueOf(num).contains("666")) {
                count++;
            }
        }

        System.out.println(num);
    }
}
