package step_16;

import java.io.*;
import java.util.*;

public class Q11 {
    public static void main(String[] args) throws IOException {
        // 1) 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());   // 자료구조 개수 N
        int[] A = new int[N];                      // A[i]=0(큐),1(스택)
        int[] B = new int[N];                      // 초기 원소 B[i]
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) B[i] = Integer.parseInt(st.nextToken());

        // 2) 큐만 추려서 초기값 리스트에 저장
        List<Integer> queueInit = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (A[i] == 0) {
                queueInit.add(B[i]);
            }
        }
        int Q = queueInit.size();  // 큐의 개수

        int M = Integer.parseInt(br.readLine()); // 수열 길이 M
        int[] C = new int[M];                    // 수열 C
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) C[i] = Integer.parseInt(st.nextToken());

        // 3) 처리 & 출력
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < M; j++) {
            int out;
            if (Q == 0) {
                // 모든 스택이면 들어온 대로 통과
                out = C[j];
            } else if (j < Q) {
                // 처음 Q개 입력은 초기 큐 상태에서 꺼낸 값
                // j=0 -> queueInit.get(Q-1), j=1 -> queueInit.get(Q-2) ...
                out = queueInit.get(Q - j - 1);
            } else {
                // 이후 입력은 Q번째 뒤의 C 값을 그대로 통과
                out = C[j - Q];
            }
            sb.append(out).append(' ');
        }
        System.out.println(sb.toString().trim());
    }
}

