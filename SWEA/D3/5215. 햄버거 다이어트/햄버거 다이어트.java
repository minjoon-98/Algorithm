import java.io.*;
import java.util.*;

public class Solution {

    static int N, L;
    static int[] scores;
    static int[] calories;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            scores = new int[N + 1];
            calories = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                scores[i] = Integer.parseInt(st.nextToken());
                calories[i] = Integer.parseInt(st.nextToken());
            }

            int[][] dp = new int[N + 1][L + 1];

            for (int i = 1; i <= N; i++) {
                for (int j = 0; j <= L; j++) {
                    if (j < calories[i]) {	// 현재 칼로리 제한 j가 재료 i의 칼로리보다 작을 경우: 재료를 포함할 수 없으므로 이전 상태를 그대로 사용
                        dp[i][j] = dp[i - 1][j];
                    } else {	// 포함할 수 있는 경우: 포함하지 않는 상태와 포함하는 상태 중 더 큰 값을 선택
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - calories[i]] + scores[i]);
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(dp[N][L]).append("\n");
        }

        System.out.print(sb.toString());
        br.close();
    }
}
