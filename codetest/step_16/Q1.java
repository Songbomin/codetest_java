package step_16;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());

            if (cmd == 1) {
                int x = Integer.parseInt(st.nextToken());
                stack.push(x);
            } else if (cmd == 2) {
                sb.append(stack.isEmpty() ? -1 : stack.pop()).append("\n");
            } else if (cmd == 3) {
                sb.append(stack.size()).append("\n");
            } else if (cmd == 4) {
                sb.append(stack.isEmpty() ? 1 : 0).append("\n");
            } else if (cmd == 5) {
                sb.append(stack.isEmpty() ? -1 : stack.peek()).append("\n");
            }
        }

        System.out.print(sb);
    }
}

