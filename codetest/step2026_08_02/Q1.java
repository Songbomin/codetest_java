package step2026_08_02;

import java.util.*;

public class Q1 {
    public String Q1(long n, String[] bans) {
        // 주문서 정렬 기준: 길이 우선, 길이가 같으면 사전순
        Arrays.sort(bans, (a, b) -> {
            if (a.length() != b.length()) {
                return Integer.compare(a.length(), b.length());
            }
            return a.compareTo(b);
        });

        long originalIndex = n;

        // 삭제된 주문이 현재 찾는 원래 순번보다 앞이면,
        // 그만큼 원래 순번을 한 칸 뒤로 밀어야 함
        for (String ban : bans) {
            long banIndex = getIndex(ban);

            if (banIndex <= originalIndex) {
                originalIndex++;
            } else {
                break;
            }
        }

        return getWord(originalIndex);
    }

    // 문자열이 삭제 전 주문서에서 몇 번째인지 반환 (1부터 시작)
    private long getIndex(String word) {
        long index = 0;
        long count = 1;

        // 더 짧은 길이의 모든 문자열 개수 더하기
        for (int len = 1; len < word.length(); len++) {
            count *= 26;
            index += count;
        }

        // 같은 길이 내에서의 사전순 위치 계산
        long value = 0;
        for (char c : word.toCharArray()) {
            value = value * 26 + (c - 'a');
        }

        return index + value + 1;
    }

    // 삭제 전 주문서의 index번째 문자열 반환 (1부터 시작)
    private String getWord(long index) {
        long count = 26;
        int length = 1;

        // 문자열 길이 찾기
        while (index > count) {
            index -= count;
            count *= 26;
            length++;
        }

        // 같은 길이 안에서 0부터 시작하는 위치로 변환
        index--;

        char[] result = new char[length];

        // 26진수처럼 뒤에서부터 문자 채우기
        for (int i = length - 1; i >= 0; i--) {
            result[i] = (char) ('a' + (index % 26));
            index /= 26;
        }

        return new String(result);
    }
}