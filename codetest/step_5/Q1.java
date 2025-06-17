package step_5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Q1 {
    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();              // 문자열 입력
        int i = Integer.parseInt(br.readLine());  // 정수 i 입력

        System.out.println(S.charAt(i - 1));   // 문자열은 0부터 시작 → i-1번째 문자 출력
    }
}

