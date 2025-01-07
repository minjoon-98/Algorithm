import java.util.Scanner;

class Solution {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();  // 테스트 케이스의 수

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();  // Ai의 크기
            int M = sc.nextInt();  // Bj의 크기
            
            int[] Ai = new int[N];
            int[] Bj = new int[M];
            
            // Ai 배열 입력 받기
            for (int i = 0; i < N; i++) {
                Ai[i] = sc.nextInt();
            }

            // Bj 배열 입력 받기
            for (int i = 0; i < M; i++) {
                Bj[i] = sc.nextInt();
            }

            // 더 긴 배열을 기준으로 겹치는 구간을 찾아 곱셈 합산
            int maxSum = Integer.MIN_VALUE;

            // 배열 Bj를 Ai 배열에 맞춰 이동시킴
            for (int offset = 0; offset <= Math.abs(N - M); offset++) {
                int sum = 0;
                // Ai의 i번째와 Bj의 i + offset 번째를 곱해서 더하기
                for (int i = 0; i < Math.min(N, M); i++) {
                    int aiIndex = i + (N > M ? offset : 0);
                    int bjIndex = i + (M > N ? offset : 0);
                    if (aiIndex < N && bjIndex < M) {
                        sum += Ai[aiIndex] * Bj[bjIndex];
                    }
                }
                maxSum = Math.max(maxSum, sum);  // 최대값 갱신
            }

            // 결과 출력
            System.out.println("#" + test_case + " " + maxSum);
        }
    }
}