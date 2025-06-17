package step_6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Q5 {
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine().toUpperCase(); // 대소문자 구분 없게 모두 대문자로

        int[] count = new int[26]; // A~Z 카운트 배열

        for (int i = 0; i < input.length(); i++) {
            count[input.charAt(i) - 'A']++;
        }

        int max = 0;
        char result = '?';
        for (int i = 0; i < 26; i++) {
            if (count[i] > max) {
                max = count[i];
                result = (char) (i + 'A');
            } else if (count[i] == max) {
                result = '?';
            }
        }

        System.out.println(result);
    }
}

