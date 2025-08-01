package step08_01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Q1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int yCost = 0;
        int mCost = 0;

        for (int i = 0; i < n; i++) {
            int time = Integer.parseInt(st.nextToken());

            yCost += ((time / 30) + 1) * 10;
            mCost += ((time / 60) + 1) * 15;
        }

        if (yCost < mCost) {
            System.out.println("Y " + yCost);
        } else if (yCost > mCost) {
            System.out.println("M " + mCost);
        } else {
            System.out.println("Y M " + yCost);
        }
    }
}
