//package a0306.homework;

import java.io.*;
import java.util.*;

public class Solution {
	
	static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	static int N;
	static char[][] map;
	static boolean[][] visited;
	
	static void bfs(int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {r, c});
		visited[r][c] = true;
		
		while (!q.isEmpty()) {
			int[] xy = q.poll();
			for (int d = 0; d < 8; d++) {
				int nx = xy[0] + dx[d];
				int ny = xy[1] + dy[d];
				if (nx>=0 && nx<N && ny>=0 && ny<N && !visited[nx][ny]) {
					if (map[nx][ny] == '0') q.offer(new int[] {nx, ny});
					visited[nx][ny] = true;
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
//			for (char[] m : map) System.out.println(Arrays.toString(m));
			visited = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				String temp = br.readLine();
				for (int j = 0; j < N; j++) {
					if (temp.charAt(j) == '.') {
						if (map[i][j] == 0) map[i][j] += '0';
					} else if (temp.charAt(j) == '*') {
						map[i][j] = '*';
						visited[i][j] = true;
						for (int d = 0; d < 8; d++) {
							int ni = i + dx[d];
							int nj = j + dy[d];
							if (ni>=0 && ni<N && nj>=0 && nj<N && map[ni][nj] != '*') {
								if (map[ni][nj] == 0) map[ni][nj] = '1';
								else map[ni][nj] += 1;
							}
						}
					}
				}
			}

			int count = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && map[i][j] == '0') {
						bfs(i, j);
						++count;
					}						
				}
			}
			
//			System.out.println(count);
//			for (char[] m : map) System.out.println(Arrays.toString(m));
//			for (boolean[] v : visited) System.out.println(Arrays.toString(v));
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && map[i][j] != '*') {
						visited[i][j] = true;
						++count;
					}						
				}
			}
			
			sb.append("#").append(tc).append(" ").append(count).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
}