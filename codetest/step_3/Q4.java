package step_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Q4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int X = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());

        int total = 0;

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int price = Integer.parseInt(input[0]);
            int count = Integer.parseInt(input[1]);

            total += price * count;
        }

        if (total == X) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}

