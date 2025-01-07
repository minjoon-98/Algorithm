import java.util.Scanner;

class Solution {
    // +형태로 스프레이 분사
    public static int calculateFliesPlus(int[][] matrix, int N, int M, int i, int j) {
        int flies = matrix[i][j];  // 중심
        // 상, 하, 좌, 우로 M칸씩 분사
        for (int k = 1; k < M; k++) {
            if (i - k >= 0) flies += matrix[i - k][j];
            if (i + k < N) flies += matrix[i + k][j];
            if (j - k >= 0) flies += matrix[i][j - k];
            if (j + k < N) flies += matrix[i][j + k];
        }
        return flies;
    }

    // x형태로 스프레이 분사
    public static int calculateFliesX(int[][] matrix, int N, int M, int i, int j) {
        int flies = matrix[i][j];  // 중심
        // 대각선으로 M칸씩 분사
        for (int k = 1; k < M; k++) {
            if (i - k >= 0 && j - k >= 0) flies += matrix[i - k][j - k];
            if (i + k < N && j + k < N) flies += matrix[i + k][j + k];
            if (i + k < N && j - k >= 0) flies += matrix[i + k][j - k];
            if (i - k >= 0 && j + k < N) flies += matrix[i - k][j + k];
        }
        return flies;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();  // 테스트 케이스 수

        // 여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            int[][] matrix = new int[N][N];

            // 행렬 입력 받기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = sc.nextInt();
                }
            }

            int maxFlies = 0;  // 최대 파리 수

            // +형태와 x형태의 스프레이를 적용하여 최댓값 구하기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    maxFlies = Math.max(maxFlies, calculateFliesPlus(matrix, N, M, i, j));
                    maxFlies = Math.max(maxFlies, calculateFliesX(matrix, N, M, i, j));
                }
            }

            System.out.println("#" + test_case + " " + maxFlies);
        }
    }
}
