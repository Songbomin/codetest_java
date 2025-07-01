package step_17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1 {
    public static void main(String[] args) throws IOException {
        // 입력을 받기 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N을 입력받음
        int N = Integer.parseInt(br.readLine());

        // 서로 다른 색 조합 수는 N * (N - 1)
        int result = N * (N - 1);

        // 결과 출력
        System.out.println(result);
    }
}
