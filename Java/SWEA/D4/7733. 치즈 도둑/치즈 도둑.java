//package a0306.homework;

import java.io.*;
import java.util.*;

public class Solution {
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int N;
	static int[][] map;
	static boolean[][] visited;
	
	static void bfs(int r, int c, int day) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {r, c});
		visited[r][c] = true;
		
		while (!q.isEmpty()) {
			int[] xy = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = xy[0] + dx[d];
				int ny = xy[1] + dy[d];
				if (nx>=0 && nx<N && ny>=0 && ny<N && !visited[nx][ny] && map[nx][ny] >= day) {
					q.offer(new int[] {nx, ny});
					visited[nx][ny] = true;
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			int minVal = 105;
			int maxVal = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					minVal = Math.min(minVal, map[i][j]);
					maxVal = Math.max(maxVal, map[i][j]);
				}
			}
			
			int answer = 0;
			for (int d = minVal; d <= maxVal; d++) {
				visited = new boolean[N][N];
				int count = 0;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (!visited[i][j] && map[i][j] >= d) {
							bfs(i, j, d);
							++count;							
						}
					}
				}
				answer = Math.max(answer, count);
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
}