package step_8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Q7 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken()); // 낮에 올라가는 높이
        int B = Integer.parseInt(st.nextToken()); // 밤에 미끄러지는 높이
        int V = Integer.parseInt(st.nextToken()); // 목표 정상 높이

        int days = (V - B - 1) / (A - B) + 1;

        System.out.println(days);
    }
}

