package step_4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Q10 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        double[] scores = new double[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        double max = 0;
        for (int i = 0; i < N; i++) {
            scores[i] = Double.parseDouble(st.nextToken());
            if (scores[i] > max) {
                max = scores[i];
            }
        }

        double sum = 0;
        for (int i = 0; i < N; i++) {
            scores[i] = scores[i] / max * 100;
            sum += scores[i];
        }

        System.out.println(sum / N);
    }
}
