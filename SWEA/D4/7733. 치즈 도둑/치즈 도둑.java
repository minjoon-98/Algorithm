import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int[][] cheese;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            cheese = new int[N][N];

            int maxTaste = 0;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    cheese[i][j] = Integer.parseInt(st.nextToken());
                    maxTaste = Math.max(maxTaste, cheese[i][j]);
                }
            }

            int maxChunks = 0;
            
            // 날짜를 기준으로 치즈 맛의 정도가 day 이하인 칸을 제외하고 DFS로 덩어리를 탐색
            for (int day = 0; day <= maxTaste; day++) {
                visited = new boolean[N][N];
                int chunks = 0;

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (!visited[i][j] && cheese[i][j] > day) {
                            dfs(i, j, day);
                            chunks++;
                        }
                    }
                }

                // maxChunks를 사용해 날짜별 덩어리 개수의 최대값을 갱신
                maxChunks = Math.max(maxChunks, chunks);
            }

            sb.append("#").append(tc).append(" ").append(maxChunks).append("\n");
        }

        System.out.print(sb.toString());
        br.close();
    }

    private static void dfs(int x, int y, int day) {
        visited[x][y] = true;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                if (!visited[nx][ny] && cheese[nx][ny] > day) {
                    dfs(nx, ny, day);
                }
            }
        }
    }
}
