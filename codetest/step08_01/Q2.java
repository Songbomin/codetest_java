package step08_01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Q2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        while (!(input = br.readLine()).equals("0")) {
            int totalWidth = 2;

            for (int i = 0; i < input.length(); i++) {
                char ch = input.charAt(i);

                if (ch == '1') {
                    totalWidth += 2;
                } else if (ch == '0') {
                    totalWidth += 4;
                } else {
                    totalWidth += 3;
                }

                if (i < input.length() - 1) {
                    totalWidth += 1;
                }
            }

            System.out.println(totalWidth);
        }
    }
}
