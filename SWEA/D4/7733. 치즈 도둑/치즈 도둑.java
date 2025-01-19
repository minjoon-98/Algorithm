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

            for (int day = 0; day <= maxTaste; day++) {
                visited = new boolean[N][N];
                int chunks = 0;

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (!visited[i][j] && cheese[i][j] > day) {
                            bfs(i, j, day);
                            chunks++;
                        }
                    }
                }

                maxChunks = Math.max(maxChunks, chunks);
            }

            sb.append("#").append(tc).append(" ").append(maxChunks).append("\n");
        }

        System.out.print(sb.toString());
        br.close();
    }

    private static void bfs(int x, int y, int day) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    if (!visited[nx][ny] && cheese[nx][ny] > day) {
                        queue.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }
}
