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


///// 오답!!!
//package a0306.homework;
//
//import java.io.*;
//import java.util.*;
//
//public class Solution_d4_7733_치즈도둑_서울_14반_김민준 {
//	
//	static int[] dx = {-1, 1, 0, 0};
//	static int[] dy = {0, 0, -1, 1};
//	
//	static int N;
//	static int[][] map;
//	static boolean[][] visited;
//	
//	static void bfs(int r, int c, int day) {
//		Queue<int[]> q = new ArrayDeque<>();
//		q.offer(new int[] {r, c});
//		visited[r][c] = true;
//		
//		while (!q.isEmpty()) {
//			int[] xy = q.poll();
//			for (int d = 0; d < 4; d++) {
//				int nx = xy[0] + dx[d];
//				int ny = xy[1] + dy[d];
//				if (nx>=0 && nx<N && ny>=0 && ny<N 
//						&& !visited[nx][ny] && map[nx][ny] >= day) {
//					q.offer(new int[] {nx, ny});
//					visited[nx][ny] = true;
//				}
//			}
//		}
//	}
//	
//	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = null;
//		StringBuilder sb = new StringBuilder();
//		
//		int T = Integer.parseInt(br.readLine());
//		
//		for (int tc = 1; tc <= T; tc++) {
//			
//			N = Integer.parseInt(br.readLine());
//			map = new int[N][N];
//			
//			// 각 맛있는 정도(1~100)에 해당하는 좌표들을 저장할 리스트
//			List<List<int[]>> cheezes = new ArrayList<>(101);
//			for (int i = 0; i < 101; i++) {
//				cheezes.add(new ArrayList<>());
//			}
//			
//			for (int i = 0; i < N; i++) {
//				st = new StringTokenizer(br.readLine());
//				for (int j = 0; j < N; j++) {
//					map[i][j] = Integer.parseInt(st.nextToken());
//
//					cheezes.get(map[i][j]).add(new int[] {i, j});
//				}
//			}
//			
//			int answer = 1;
//            // 1부터 100까지 각 날에 대해 안전한 치즈 덩어리 개수를 계산
//			for (int day = 1; day < 101; day++) {
//				if (cheezes.get(day).isEmpty()) continue;
//				visited = new boolean[N][N];
//				int count = 0;
//				// 맛이 day 이상인 모든 치즈 좌표들을 순회
//                for (int taste = day; taste <= 100; taste++) {
//                	// 해당 맛있는 정도를 가진 좌표들에서 BFS 시작
//                	for (int[] coordinate : cheezes.get(day)) {
//                		if (visited[coordinate[0]][coordinate[1]]) continue;
//                		bfs(coordinate[0], coordinate[1], day);
//                		++count;
//                	}
//				}
//				answer = Math.max(answer, count);
//			}
//			
//			sb.append("#").append(tc).append(" ").append(answer).append("\n");
//		}
//		
//		System.out.println(sb.toString());
//	}
//	
//}
