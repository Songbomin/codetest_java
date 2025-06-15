package step_4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Q2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLine = br.readLine().split(" ");
        int N = Integer.parseInt(firstLine[0]);
        int X = Integer.parseInt(firstLine[1]);

        String[] nums = br.readLine().split(" ");

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int value = Integer.parseInt(nums[i]);
            if (value < X) {
                sb.append(value).append(" ");
            }
        }

        System.out.println(sb.toString());
    }
}

