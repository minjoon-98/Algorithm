/// bfs로 도전!!!
//package a0227.homework;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	static int[][] map, path;
	
	static void bfs(int r, int c) {
		
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {r, c});
		
		while (!q.isEmpty()) {
			int[]xy = q.poll();
			int x = xy[0], y = xy[1];
			
			if (x == N-1 && y == N-1) {
				continue;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i], ny = y + dy[i];
				if (nx>=0 && nx<N && ny>=0 && ny<N) {
					int currentValue = path[x][y] + map[nx][ny];
					// 가지치기
					if (currentValue < path[nx][ny]) {
						path[nx][ny] = currentValue;
						q.offer(new int[] {nx, ny});
					}
				}
			}
		}
		return;
	}
	
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = 0;
		
		while ((N = Integer.parseInt(br.readLine())) != 0) {
			
			map = new int[N][N];
			path = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					path[i][j] = Integer.MAX_VALUE;
				}
			}
			
			path[0][0] = map[0][0];
//			answer = Integer.MAX_VALUE;
			bfs(0, 0);
			sb.append("Problem ").append(++tc).append(": ").append(path[N-1][N-1]).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}


///// 25% 시간 초과
//package a0227.homework;
//
//import java.io.*;
//import java.util.*;
//
//public class Main_bj_4485_녹색옷입은애가젤다지_서울_14반_김민준 {
//	
//	static int N, M;
//	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
//	static int[][] map, path;
////	static boolean[][] visited;
////	static int answer;
//	
//	static void dfs(int x, int y) {
//		
//		// 탈출 조건
//		if (x == N-1 && y == N-1) {
//			return;
//		}
//		
//		for (int i = 0; i < 4; i++) {
//			int nx = x + dx[i], ny = y + dy[i];
//			if (nx>=0 && nx<N && ny>=0 && ny<N) {
//				int currentValue = path[x][y] + map[nx][ny];
//				// 가지치기
//				if (currentValue < path[nx][ny]) {
//					path[nx][ny] = currentValue;
//					backtracking(nx, ny);
//				}
//			}
//		}
//	}
//	
//	
//	public static void main(String[] args) throws Exception {
//
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
//		int tc = 0;
//		
//		while ((N = Integer.parseInt(br.readLine())) != 0) {
//			
//			map = new int[N][N];
//			path = new int[N][N];
//
//			for (int i = 0; i < N; i++) {
//				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//				for (int j = 0; j < N; j++) {
//					map[i][j] = Integer.parseInt(st.nextToken());
//					path[i][j] = Integer.MAX_VALUE;
//				}
//			}
//			
//			path[0][0] = map[0][0];
////			answer = Integer.MAX_VALUE;
//			backtracking(0, 0);
//			sb.append("Problem ").append(++tc).append(": ").append(path[N-1][N-1]).append("\n");
//		}
//		
//		System.out.println(sb.toString());
//	}
//}
