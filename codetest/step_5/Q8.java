package step_5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Q8 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine().trim(); // 앞뒤 공백 제거

        if (line.isEmpty()) {
            System.out.println(0);
        } else {
            String[] words = line.split(" ");
            System.out.println(words.length);
        }
    }
}
