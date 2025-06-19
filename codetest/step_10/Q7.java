package step_10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Q7 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] side = new int[3];
            side[0] = Integer.parseInt(st.nextToken());
            side[1] = Integer.parseInt(st.nextToken());
            side[2] = Integer.parseInt(st.nextToken());

            if (side[0] == 0 && side[1] == 0 && side[2] == 0) break;

            Arrays.sort(side);

            int a = side[0], b = side[1], c = side[2];

            if (c >= a + b) {
                System.out.println("Invalid");
            } else if (a == b && b == c) {
                System.out.println("Equilateral");
            } else if (a == b || b == c || a == c) {
                System.out.println("Isosceles");
            } else {
                System.out.println("Scalene");
            }
        }
    }
}
