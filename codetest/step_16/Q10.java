package step_16;

import java.io.*;
import java.util.*;

public class Q10 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        Deque<int[]> deque = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            int move = Integer.parseInt(st.nextToken());
            deque.offer(new int[]{i, move});
        }

        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            int[] balloon = deque.pollFirst();
            int num = balloon[0];
            int move = balloon[1];
            sb.append(num).append(" ");

            if (deque.isEmpty()) break;

            // 회전
            if (move > 0) {
                for (int i = 1; i < move; i++) {
                    deque.offerLast(deque.pollFirst());
                }
            } else {
                for (int i = 0; i < Math.abs(move); i++) {
                    deque.offerFirst(deque.pollLast());
                }
            }
        }

        System.out.println(sb);
    }
}

