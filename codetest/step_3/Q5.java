package step_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Q5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int repeat = N / 4;
        String result = "";

        for (int i = 0; i < repeat; i++) {
            result += "long ";
        }

        result += "int";

        System.out.println(result);
    }
}

