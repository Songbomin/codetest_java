package step_13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class Q1 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 수의 개수
        int[] nums = new int[N];

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine()); // 숫자 입력
        }

        Arrays.sort(nums); // 정렬

        // 빠른 출력
        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(num).append('\n');
        }

        System.out.print(sb); // 출력
    }
}
