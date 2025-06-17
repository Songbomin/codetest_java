package step_6;

import java.io.IOException;

public class Q5_1 {
    public static void main(String[] args) throws IOException {
        int[] count = new int[26];  // 알파벳 개수 저장용
        int c;

        // 입력이 공백 문자(줄 바꿈 포함)보다 크면 계속 읽음
        while ((c = System.in.read()) > 32) {
            // 소문자는 대문자로 변환: 'a'~'z'(97~122) → 'A'~'Z'(65~90)
            if (c >= 'a') c -= 32;

            count[c - 'A']++;  // 해당 알파벳 카운트 증가
        }

        int max = -1;
        char result = '?';

        for (int i = 0; i < 26; i++) {
            if (count[i] > max) {
                max = count[i];
                result = (char)(i + 'A');
            } else if (count[i] == max) {
                result = '?';
            }
        }

        System.out.println(result);
    }
}

