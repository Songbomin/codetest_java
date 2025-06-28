package step_14;

import java.io.*;
import java.util.*;

public class Q4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<String, Integer> nameToNumber = new HashMap<>();
        String[] numberToName = new String[N + 1];

        // 도감 정보 입력
        for (int i = 1; i <= N; i++) {
            String name = br.readLine();
            nameToNumber.put(name, i);
            numberToName[i] = name;
        }

        // 문제 처리
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String query = br.readLine();
            if (isNumber(query)) {
                int idx = Integer.parseInt(query);
                sb.append(numberToName[idx]).append("\n");
            } else {
                sb.append(nameToNumber.get(query)).append("\n");
            }
        }

        System.out.print(sb);
    }

    // 숫자인지 문자열인지 판별하는 함수
    private static boolean isNumber(String s) {
        return s.charAt(0) >= '0' && s.charAt(0) <= '9';
    }
}
