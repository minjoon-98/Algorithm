import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int[][] dp = new int[N][M];
        
        dp[0][0] = map[0][0];
        for (int j = 1; j < M; j++) {
            dp[0][j] = dp[0][j - 1] + map[0][j];
        }

        for (int i = 1; i < N; i++) {

            // left 배열: 현재 행에서 왼쪽에서 오른쪽으로 이동하며 도달 가능한 최대 누적합 계산
            int[] left = new int[M];
            left[0] = dp[i - 1][0] + map[i][0]; // 맨 왼쪽 칸은 반드시 바로 위에서 내려온 dp[i-1][0]에 현재 값 map[i][0]을 더한 값
            for (int j = 1; j < M; j++) {
                left[j] = Math.max(dp[i - 1][j], left[j - 1]) + map[i][j];
            }
            
            // right 배열: 현재 행에서 오른쪽에서 왼쪽으로 이동하며 도달 가능한 최대 누적합 계산
            int[] right = new int[M];
            right[M - 1] = dp[i - 1][M - 1] + map[i][M - 1]; // 맨 오른쪽 칸은 반드시 바로 위에서 내려온 dp[i-1][M-1]에 현재 값 map[i][M-1]을 더한 값
            for (int j = M - 2; j >= 0; j--) {
                right[j] = Math.max(dp[i - 1][j], right[j + 1]) + map[i][j];
            }
            
            for (int j = 0; j < M; j++) {
                dp[i][j] = Math.max(left[j], right[j]);
            }
        }
        
        System.out.println(dp[N - 1][M - 1]);
    }
}