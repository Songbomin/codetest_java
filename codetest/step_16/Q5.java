package step_16;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Q5{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            queue.offer(Integer.parseInt(st.nextToken()));
        }

        int now = 1;

        while (!queue.isEmpty()) {
            int front = queue.poll();

            if (front == now) {
                now++; // 바로 간식 받을 수 있음
            } else {
                stack.push(front);
            }

            while (!stack.isEmpty() && stack.peek() == now) {
                stack.pop();
                now++;
            }
        }

        while (!stack.isEmpty() && stack.peek() == now) {
            stack.pop();
            now++;
        }

        System.out.println(stack.isEmpty() ? "Nice" : "Sad");
    }
}

