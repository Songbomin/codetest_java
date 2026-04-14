package step04_15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2 {

    static int[] left = new int[26];
    static int[] right = new int[26];
    static StringBuilder sb = new StringBuilder();

    public static void preorder(int node) {
        if (node == -1) {
            return;
        }

        sb.append((char) (node + 'A'));
        preorder(left[node]);
        preorder(right[node]);
    }

    public static void inorder(int node) {
        if (node == -1) {
            return;
        }

        inorder(left[node]);
        sb.append((char) (node + 'A'));
        inorder(right[node]);
    }

    public static void postorder(int node) {
        if (node == -1) {
            return;
        }

        postorder(left[node]);
        postorder(right[node]);
        sb.append((char) (node + 'A'));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < 26; i++) {
            left[i] = -1;
            right[i] = -1;
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            char parentChar = st.nextToken().charAt(0);
            char leftChar = st.nextToken().charAt(0);
            char rightChar = st.nextToken().charAt(0);

            int parent = parentChar - 'A';

            if (leftChar != '.') {
                left[parent] = leftChar - 'A';
            }

            if (rightChar != '.') {
                right[parent] = rightChar - 'A';
            }
        }

        preorder(0);
        sb.append('\n');

        inorder(0);
        sb.append('\n');

        postorder(0);

        System.out.print(sb.toString());
    }
}
