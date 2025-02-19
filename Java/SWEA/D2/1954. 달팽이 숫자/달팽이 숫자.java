//package a0219.camp;

import java.util.*;
import java.io.*;

public class Solution {

	// 우하좌상 ( 달팽이 회전 방향 )
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	static int N;
	static int[][] map;
	static boolean[][] visited;
	
	static void dfs(int x, int y, int direction, int depth) {
		
		if (depth == N*N + 1) {
			return;
		}
		
		map[x][y] = depth;
		visited[x][y] = true;
		
		int nx = x + dx[direction];
		int ny = y + dy[direction];
		
		if (0<=nx && nx<N && 0<=ny && ny<N && !visited[nx][ny]) {
			dfs(nx, ny, direction, depth+1);
		} else {
			direction = (direction+1)%4;
			nx = x + dx[direction];
			ny = y + dy[direction];
			dfs(nx, ny, direction, depth+1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			visited = new boolean[N][N];
			
			dfs(0, 0, 0, 1);
			
			sb.append("#").append(tc);
			sb.append("\n");
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(map[i][j]).append(" ");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
	}

}
