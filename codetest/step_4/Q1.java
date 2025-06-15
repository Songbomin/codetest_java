package step_4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Q1 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] numbers = br.readLine().split(" ");

        int v = Integer.parseInt(br.readLine());

        int count = 0;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(numbers[i]);
            if (num == v) {
                count++;
            }
        }

        System.out.println(count);
    }
}

