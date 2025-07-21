package step07_21;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Q2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Set<String> set = new HashSet<>(); // 중복 제거용
        for (int i = 0; i < N; i++) {
            set.add(br.readLine());
        }

        List<String> list = new ArrayList<>(set);

        Collections.sort(list, new Comparator<String>() {
            public int compare(String a, String b) {
                if (a.length() == b.length()) {
                    return a.compareTo(b);
                } else {
                    return a.length() - b.length();
                }
            }
        });

        // 출력 처리
        for (String word : list) {
            System.out.println(word);
        }
    }
}

