import java.util.Scanner;

class Solution {
    // 90도 시계방향 회전 함수
    public static int[][] rotate90(int[][] matrix, int N) {
        int[][] rotated = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                rotated[j][N - i - 1] = matrix[i][j];
            }
        }
        return rotated;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();  // 테스트 케이스 수

        // 여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();  // 행렬의 크기 N
            int[][] matrix = new int[N][N];  // N x N 행렬

            // 행렬 입력 받기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = sc.nextInt();
                }
            }

            // 90도, 180도, 270도 회전된 행렬을 구한다.
            int[][] rot90 = rotate90(matrix, N);
            int[][] rot180 = rotate90(rot90, N);
            int[][] rot270 = rotate90(rot180, N);

            // 결과 출력
            System.out.println("#" + test_case);
            for (int i = 0; i < N; i++) {
                // 각 회전된 행렬을 공백을 기준으로 한 줄로 출력
                for (int j = 0; j < N; j++) {
                    System.out.print(rot90[i][j]);
                }
                System.out.print(" ");
                for (int j = 0; j < N; j++) {
                    System.out.print(rot180[i][j]);
                }
                System.out.print(" ");
                for (int j = 0; j < N; j++) {
                    System.out.print(rot270[i][j]);
                }
                System.out.println();
            }
        }
    }
}
