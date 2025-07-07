package step_20;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Q2 {
    static int N;
    static int[] numbers;
    static int[] operators = new int[4]; // +, -, *, /
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        numbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            numbers[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++)
            operators[i] = Integer.parseInt(st.nextToken());

        dfs(1, numbers[0]);
        System.out.println(max);
        System.out.println(min);
    }

    static void dfs(int depth, int current) {
        if (depth == N) {
            max = Math.max(max, current);
            min = Math.min(min, current);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operators[i] > 0) {
                operators[i]--;
                int next = calc(current, numbers[depth], i);
                dfs(depth + 1, next);
                operators[i]++;
            }
        }
    }

    static int calc(int a, int b, int op) {
        switch (op) {
            case 0: return a + b;
            case 1: return a - b;
            case 2: return a * b;
            case 3:
                if (a < 0) return -(-a / b);
                else return a / b;
        }
        return 0;
    }
}
