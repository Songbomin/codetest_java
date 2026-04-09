package step04_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N < 2) {
            System.out.println(0);
            return;
        }

        boolean[] isComposite = new boolean[N + 1];

        for (int i = 2; i * i <= N; i++) {
            if (isComposite[i]) {
                continue;
            }

            for (int j = i * i; j <= N; j += i) {
                isComposite[j] = true;
            }
        }

        int[] primes = new int[N + 1];
        int size = 0;

        for (int i = 2; i <= N; i++) {
            if (!isComposite[i]) {
                primes[size++] = i;
            }
        }

        int left = 0;
        int right = 0;
        int sum = 0;
        int count = 0;

        while (true) {
            if (sum >= N) {
                if (sum == N) {
                    count++;
                }
                sum -= primes[left++];
            } else {
                if (right == size) {
                    break;
                }
                sum += primes[right++];
            }
        }

        System.out.println(count);
    }
}
