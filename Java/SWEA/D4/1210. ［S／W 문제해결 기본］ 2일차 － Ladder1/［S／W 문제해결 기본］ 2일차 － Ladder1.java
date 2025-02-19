import java.util.*;
import java.io.*;

public class Solution {
	
	// 역방향 사다리 타기(내려가지 않는다) (좌, 우, 상) 우선순위
	static int[] dx = {0, 0, -1};
	static int[] dy = {-1, 1, 0};
	
	static int[] dest = new int[2];
	
	static int map[][] = new int[100][100];
	static boolean[][] visited;

	static int ladder(int r, int c) {
		int start = -1; 
		
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {r, c});
		visited[r][c] = true;
		
		while (!q.isEmpty()) {
			int[] xy = q.poll();
			
			if (xy[0] == 0) {
				start = xy[1];
			}
			
			for (int i = 0; i < 3; i++) {
				
				int nx = xy[0] + dx[i];
				int ny = xy[1] + dy[i];
				
				if (0<=nx && nx<100 && 0<=ny && ny<100 && !visited[nx][ny] && map[nx][ny] == 1) {
					q.offer(new int[] {nx, ny});
					visited[nx][ny] = true;
					break;
				}
			}
		}
		
		return start;
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
//		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= 10; tc++) {
			
			int test_case = Integer.parseInt(br.readLine());
			
			
			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (i == 99 && map[i][j] ==2) {
						dest[0] = i;
						dest[1] = j;
					}
				}
			}
			visited = new boolean[100][100];
			
			int answer = ladder(dest[0], dest[1]);
			
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

}
