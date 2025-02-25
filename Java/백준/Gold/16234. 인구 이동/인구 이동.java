import java.io.*;
import java.util.*;

public class Main {

	static int N, L, R;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] map;
	static boolean[][] visited;
	
	private static boolean bfs(int r, int c) {
		
		boolean isOpen = false;
		Queue<int[]> q = new ArrayDeque<>();
		ArrayDeque<int[]> s = new ArrayDeque<>();
		q.offer(new int[] {r, c});
		s.push(new int[] {r, c});
		visited[r][c] = true;
		int sum = map[r][c];
		
		while (!q.isEmpty()) {
			int[] xy = q.poll();
			int x = xy[0], y = xy[1];
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i], ny = y + dy[i];
				if (0<=nx && nx<N && 0<=ny && ny<N && !visited[nx][ny]) {
					int sub = Math.abs(map[x][y] - map[nx][ny]);
					if (L<=sub && sub<=R) {
						isOpen = true;
						q.offer(new int[] {nx, ny});
						s.push(new int[] {nx, ny});
						visited[nx][ny] = true;
						sum += map[nx][ny];
					}
				}
			}
		}
		
		int avg = sum / s.size();
		while (!s.isEmpty()) {
			int[] xy = s.pop();
			int x = xy[0], y = xy[1];
			map[x][y] = avg;
		}			
		
		return isOpen;
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean moved = true;
		int count = 0;
		
		while (moved) {
			moved = false;
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && bfs(i, j)) {
						moved = true;
					}
				}
			}
			if (moved) ++count;
		}

		System.out.println(count);
	}
}
