package step_3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Q8 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        for (int i = 1; i <= T; i++) {
            String[] input = br.readLine().split(" ");
            int A = Integer.parseInt(input[0]);
            int B = Integer.parseInt(input[1]);
            int C = A + B;


            sb.append("Case #").append(i)
                    .append(": ").append(A)
                    .append(" + ").append(B)
                    .append(" = ").append(C)
                    .append("\n");
        }

        // 조립한 문자열을 한번에 출력
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}

