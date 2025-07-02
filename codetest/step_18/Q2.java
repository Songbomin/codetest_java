package step_18;

import java.util.*;
import java.io.*;

public class Q2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        int sum = 0;

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
            sum += nums[i];
        }

        Arrays.sort(nums);

        int median = nums[N / 2];

        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> freqList = new ArrayList<>(freq.entrySet());
        freqList.sort((a, b) -> {
            if (b.getValue().equals(a.getValue())) {
                return a.getKey() - b.getKey();
            } else {
                return b.getValue() - a.getValue();
            }
        });

        int mode = freqList.get(0).getKey();
        if (freqList.size() > 1 && freqList.get(0).getValue().equals(freqList.get(1).getValue())) {
            mode = freqList.get(1).getKey();
        }

        // 출력
        System.out.println(Math.round((double) sum / N));
        System.out.println(median);
        System.out.println(mode);
        System.out.println(nums[N - 1] - nums[0]);
    }
}

