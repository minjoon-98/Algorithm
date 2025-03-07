//package a0307.homework;

import java.io.*;
import java.util.*;

public class Solution {

	// 0: 우하, 1: 좌하, 2: 좌상, 3: 우상
	static int[] dx = {1, 1, -1, -1};
	static int[] dy = {1, -1, -1, 1};
	
	static int N;
	static int[][] map;
	
	static int answer;
	
	static void check(int x, int y, int slashCnt, int backSlashCnt, boolean[] visited, int tc) {
		
		visited[map[x][y]] = true;
		int count = 1; 
		
		int cx = x;
		int cy = y;
		
		for (int i = 1; i < slashCnt; i++) {
			cx = cx + dx[0];
			cy = cy + dy[0];
			if (cx>=0 && cx<N && cy>=0 && cy<N) {
				if (!visited[map[cx][cy]]) {
					visited[map[cx][cy]] = true;
					++count;
				} else {
					return;
				}
			} else {
				return;
			}
		}
		
		for (int i = 1; i < backSlashCnt; i++) {
			cx = cx + dx[1];
			cy = cy + dy[1];
			if (cx>=0 && cx<N && cy>=0 && cy<N) {
				if (!visited[map[cx][cy]]) {
					visited[map[cx][cy]] = true;
					++count;
				} else {
					return;
				}
			} else {
				return;
			}
		}

		for (int i = 1; i < slashCnt; i++) {
			cx = cx + dx[2];
			cy = cy + dy[2];
			if (cx>=0 && cx<N && cy>=0 && cy<N) {
				if (!visited[map[cx][cy]]) {
					visited[map[cx][cy]] = true;
					++count;
				} else {
					return;
				}
			} else {
				return;
			}
		}
		
		for (int i = 1; i < backSlashCnt - 1; i++) {
			cx = cx + dx[3];
			cy = cy + dy[3];
			if (cx>=0 && cx<N && cy>=0 && cy<N) {
				if (!visited[map[cx][cy]]) {
					visited[map[cx][cy]] = true;
					++count;
				} else {
					return;
				}
			} else {
				return;
			}
		}
		
		answer = Math.max(answer, count);

	}
	
	public static void main(String[] args)  throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
//			int[][] rectangles = new int[N*(N+1)/2][2];
			List<int[]> rectangles = new ArrayList<>();
			
			for (int i = 2; i < N; i++) {
				for (int j = N + 1 -i; j >= 2; j--) {
					rectangles.add(new int[] {i, j});
				}
			}
			
//			for (int[] rectangle : rectangles) System.out.println(N + " : " + Arrays.toString(rectangle));
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			answer = -1;
			for (int[] rectangle : rectangles) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						check(i, j, rectangle[0], rectangle[1], new boolean[101], tc);
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
}

///// dfs 포기!
//package a0307.homework;
//
//import java.io.*;
//import java.util.*;
//
//public class Solution_ur_2105_디저트카페_서울_14반_김민준 {
//
//	// 0: 우하, 1: 좌하, 2: 좌상, 3: 우상
//	static int[] dx = {-1, -1, 1, 1};
//	static int[] dy = {1, -1, -1, 1};
//	
//	static int N;
//	static int[][] map;
//	
//	static int answer;
//	
//	static void dfs(int x, int y, int direction, int sum, boolean[] visited, int[] start) {
//		
//		if (direction == 4) {
//			return;
//		} else if (direction == 3 && start[0] == x && start[1] == y) {
//			answer = Math.max(answer, sum);
//			return;
//		}
//		
//		visited[map[x][y]] = true;
//		
//		int nx = x + dx[direction];
//		int ny = y + dy[direction];
//		if (nx>=0 && nx<N && ny>=0 && ny<N) {
//			if (!visited[map[nx][ny]]) {
//				visited[map[nx][ny]] = true;
//				dfs(nx, ny, direction, sum + map[x][y], visited, start);
//			} else if (start[0] == nx && start[1] == ny) {
//				dfs(nx, ny, direction, sum + map[x][y], visited, start);				
//			}
//		}
//		if (direction < 3) {			
//			nx = x + dx[direction+1];
//			ny = y + dy[direction+1];
//			if (nx>=0 && nx<N && ny>=0 && ny<N) {
//				if (!visited[map[nx][ny]]) {
//					visited[map[nx][ny]] = true;
//					dfs(nx, ny, direction, sum + map[x][y], visited, start);
//				} else if (start[0] == nx && start[1] == ny) {
//					dfs(nx, ny, direction, sum + map[x][y], visited, start);				
//				}
//			}	
//		}
//
//	}
//	
//	
//	public static void main(String[] args)  throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
//		StringTokenizer st = null;
//		
//		int T = Integer.parseInt(br.readLine());
//		
//		for (int tc = 1; tc <= T; tc++) {
//
//			N = Integer.parseInt(br.readLine());
//			map = new int[N][N];
//			
//			for (int i = 0; i < N; i++) {
//				st = new StringTokenizer(br.readLine(), " ");
//				for (int j = 0; j < N; j++) {
//					map[i][j] = Integer.parseInt(st.nextToken());
//				}
//			}
//			
//			answer = -1;
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					dfs(i, j, 0, 0, new boolean[101], new int[] {i, j});
//				}
//			}
//			
//			sb.append("#").append(tc).append(" ").append(answer).append("\n");
//		}
//		System.out.println(sb.toString());
//	}
//}
