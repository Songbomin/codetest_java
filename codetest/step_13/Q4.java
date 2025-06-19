package step_13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class Q4 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(nums); // 정렬

        // 빠른 출력
        StringBuilder sb = new StringBuilder();
        for (int n : nums) {
            sb.append(n).append('\n');
        }
        System.out.print(sb); // 한 번에 출력
    }
}

