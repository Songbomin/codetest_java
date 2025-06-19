package step_12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Q6 {
    public static void main(String[] args) throws IOException {
        // 빠른 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int result = -1;

        for (int five = N / 5; five >= 0; five--) {
            int remain = N - 5 * five;
            if (remain % 3 == 0) {
                int three = remain / 3;
                result = five + three;
                break;
            }
        }

        System.out.println(result);
    }
}

