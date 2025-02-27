//package a0227.homework;

import java.io.*;
import java.util.*;

public class Solution {

	static int N, M;
	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	static int[][] map;
	
	static int[] bfs(int r, int c) {
		
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {r, c, 1});
		
		int[] result = {map[r][c], 1};
		
		while (!q.isEmpty()) {
			int[]xy = q.poll();
			int x = xy[0], y = xy[1], count = xy[2];
			
			if (count > result[1]) {
				result[1] = count;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i], ny = y + dy[i];
				if (nx>=0 && nx<N && ny>=0 && ny<N) {
					if (map[x][y]+1 == map[nx][ny]) {
						q.offer(new int[] {nx, ny, count+1});
					}
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[] answer = {-1, 0};
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int[] result = bfs(i, j);
					if (result[1] > answer[1]) {
						answer = result;
					} else if (result[1] == answer[1]) {
						answer[0] = Math.min(answer[0], result[0]);
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer[0]).append(" ").append(answer[1]).append("\n");
		}		
		System.out.println(sb.toString());
	}	
}

///// 오답
///// 답이 같은 경우 더 작은 값을 출력하는 조건 미충
///// 채점용 input 파일로 채점한 결과 fail 입니다.
///// (오답 : 27개의 테스트케이스 중 11개가 맞았습니다.)
//package a0226.homework;
//
//import java.io.*;
//import java.util.*;
//
//public class Solution_d4_1861_정사각형방_서울_14반_김민준 {
//
//	static int N, M;
//	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
//	static int[][] map;
//	
//	static int[] bfs(int r, int c) {
//		
//		Queue<int[]> q = new ArrayDeque<>();
//		q.offer(new int[] {r, c, 1});
//		
//		int[] result = {map[r][c], 1};
//		
//		while (!q.isEmpty()) {
//			int[]xy = q.poll();
//			int x = xy[0], y = xy[1], count = xy[2];
//			
//			if (count > result[1]) {
//				result[1] = count;
//			}
//			
//			for (int i = 0; i < 4; i++) {
//				int nx = x + dx[i], ny = y + dy[i];
//				if (nx>=0 && nx<N && ny>=0 && ny<N) {
//					if (map[x][y]+1 == map[nx][ny]) {
//						q.offer(new int[] {nx, ny, count+1});
//					}
//				}
//			}
//		}
//		return result;
//	}
//	
//	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
//		
//		int T = Integer.parseInt(br.readLine());
//		
//		for (int tc = 1; tc <= T; tc++) {
//			
//			N = Integer.parseInt(br.readLine());
//			map = new int[N][N];
//			
//			for (int i = 0; i < N; i++) {
//				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//				for (int j = 0; j < N; j++) {
//					map[i][j] = Integer.parseInt(st.nextToken());
//				}
//			}
//			
//			int[] answer = {-1, 0};
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					int[] result = bfs(i, j);
//					if (result[1] > answer[1]) {
//						answer = result;
//					}
//				}
//			}
//			
//			sb.append("#").append(tc).append(" ").append(answer[0]).append(" ").append(answer[1]).append("\n");
//		}		
//		System.out.println(sb.toString());
//	}	
//}
