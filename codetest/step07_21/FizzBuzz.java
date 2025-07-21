package step07_21;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class FizzBuzz {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = new String[3];
        for (int i = 0; i < 3; i++) {
            input[i] = br.readLine();
        }

        int num = 1;
        while (true) {
            String a = getFizzBuzz(num);
            String b = getFizzBuzz(num + 1);
            String c = getFizzBuzz(num + 2);

            if (a.equals(input[0]) && b.equals(input[1]) && c.equals(input[2])) {
                System.out.println(getFizzBuzz(num + 3));
                break;
            }

            num++;
        }
    }

    // FizzBuzz 결과 생성 함수
    private static String getFizzBuzz(int n) {
        if (n % 3 == 0 && n % 5 == 0) return "FizzBuzz";
        else if (n % 3 == 0) return "Fizz";
        else if (n % 5 == 0) return "Buzz";
        else return String.valueOf(n);
    }
}

