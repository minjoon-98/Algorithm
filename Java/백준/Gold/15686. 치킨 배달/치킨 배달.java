//package a0226.homework;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static int answer = Integer.MAX_VALUE;
	static int[][] map;
	static List<int[]> houses, chickens;
	static boolean[]selected;
	
	static void combination(int index, int count) {
		if (count == M) {
			answer = Math.min(answer, getChickenDistance());
			return;
		}
		
		if (index == chickens.size()) return;
		
		selected[index] = true; // 치킨집을 선택한 경우
		combination(index + 1, count + 1);
		selected[index] = false; // 치킨집을 선택하지 않은 경우
		combination(index + 1, count);
		
	}
	
	static int getChickenDistance() {
		int totalDist = 0;
		for (int[] house : houses) {
			int minDist = Integer.MAX_VALUE;
			for (int i = 0; i < chickens.size(); i++) {
				if (selected[i]) {
					int[] chicken = chickens.get(i);
					int dist = Math.abs(chicken[0] - house[0]) + Math.abs(chicken[1] - house[1]);
					minDist = Math.min(minDist, dist);					
				}
			}
			totalDist += minDist;
		}
		return totalDist;
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		houses = new ArrayList<>();
		chickens = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					houses.add(new int[] {i, j});
				} else if (map[i][j] == 2) {
					chickens.add(new int[] {i, j});
				}
			}
		}
		
		selected = new boolean[chickens.size()];
		combination(0, 0);
		System.out.println(answer);
	}
}

/// 부분집합으로 풀었지만 StackOverflowError 발생
/// 불필요하게 너무 깊은 재귀호출을 수행
//package a0226.homework;
//
//import java.io.*;
//import java.util.*;
//
//public class Main_bj_15686_치킨배달_서울_14반_김민준 {
//	
//	static int N, M;
//	static int answer = Integer.MAX_VALUE;
//	static int[][] map;
//	static boolean[][] visited;
//	static List<int[]> houses, chickens;
//	
//	static void subset(int index, int pickCount, int chickenCnt, List<int[]> selectedChickens) {
//		if (index == chickenCnt || pickCount == M) {
//			visited = new boolean[N][N];
//			int sum = 0;
//			for (int[] xy : houses) {
//				int x = xy[0], y = xy[1];
//				int minDist = Integer.MAX_VALUE;
//				for (int i = 0; i < selectedChickens.size(); i++) {
//					int[] chicken = selectedChickens.get(i);
//					int dist = Math.abs(chicken[0] - x) + Math.abs(chicken[1] - y);
//					if (dist < minDist) {
//						minDist = dist;
//					}
//				}
//				sum += minDist;
//				visited[x][y] = true;
//			}
//			answer = Math.min(answer, sum);
//		}
//		
//		if (pickCount < M) {
//			selectedChickens.add(pickCount, chickens.get(index));
//			subset(index+1, pickCount+1, chickenCnt, selectedChickens);
//			selectedChickens.remove(pickCount);
//		}
//		subset(index+1, pickCount, chickenCnt, selectedChickens);
//	}
//	
//	public static void main(String[] args) throws Exception {
//
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//		N = Integer.parseInt(st.nextToken());
//		M = Integer.parseInt(st.nextToken());
//		
//		map = new int[N][N];
//		
//		houses = new ArrayList<>();
//		chickens = new ArrayList<>();
//		int chickenCnt = 0;
//		
//		for (int i = 0; i < N; i++) {
//			st = new StringTokenizer(br.readLine(), " ");
//			for (int j = 0; j < N; j++) {
//				map[i][j] = Integer.parseInt(st.nextToken());
//				if (map[i][j] == 1) {
//					houses.add(new int[] {i, j});
//				} else if (map[i][j] == 2) {
//					chickens.add(new int[] {i, j});
//					++chickenCnt;
//				}
//			}
//		}
//		
//		subset(0, 0, chickenCnt, new ArrayList<>());
//		
//		System.out.println(answer);
//	}
//}


/// 그리디 오답
/// 단순히 각 집에서 가장 가까운 치킨집을 선택하는 방식이 전체적으로 최적의 해가 되지 않음
/// 따라서 다른 방법으로 풀어야함
//package a0226.homework;
//
//import java.io.*;
//import java.util.*;
//
//public class Main_bj_15686_치킨배달_서울_14반_김민준 {
//	
//	public static void main(String[] args) throws Exception {
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//		int N = Integer.parseInt(st.nextToken());
//		int M = Integer.parseInt(st.nextToken());
//		
//		int[][] map = new int[N][N];
//		
//		ArrayList<int[]> houses = new ArrayList<>();
//		List<int[]> chickens = new ArrayList<>();
//		
//		for (int i = 0; i < N; i++) {
//			st = new StringTokenizer(br.readLine(), " ");
//			for (int j = 0; j < N; j++) {
//				map[i][j] = Integer.parseInt(st.nextToken());
//				if (map[i][j] == 1) {
//					houses.add(new int[] {i, j});
//				} else if (map[i][j] == 2) {
//					chickens.add(new int[] {i, j, Integer.MAX_VALUE, Integer.MAX_VALUE});
//				}
//			}
//		}
//		
//		for (int i = 0; i < chickens.size(); i++) {
//			int dist = 0;
//			int[] chickenXY = chickens.get(i);
//			int chickenX = chickenXY[0], chickenY = chickenXY[1];
//			for (int[] xy : houses) {
//				dist += Math.abs(chickenX - xy[0]) + Math.abs(chickenY - xy[1]);
//			}
//			chickens.get(i)[3] = dist;
//		}
//		
//		boolean[][] visited = new boolean[N][N];
//		for (int[] xy : houses) {
//			int x = xy[0], y = xy[1];
//			int minIdx = 0;
//			int minDist = Integer.MAX_VALUE;
//			for (int i = 0; i < chickens.size(); i++) {
//				int[] chicken = chickens.get(i);
//				int dist = Math.abs(chicken[0] - x) + Math.abs(chicken[1] - y);
//				if (dist < minDist) {
//					minDist = dist;
//					minIdx = i;
//				}
//			}
//			chickens.get(minIdx)[2] = minDist;
//			visited[x][y] = true;
//		}
//		
////		for (int[] c : chickens) System.out.println(Arrays.toString(c));
//		
//		chickens.sort((o1, o2) -> o1[2] != o2[2] ? o1[2] - o2[2] : o1[3] - o2[3]);	// 치킨 거리 기준으로 오름차순으로 정렬
//		
//		/// 제대로 제거가 안됨...
////		for (int i = 0; i < chickens.size() - M; i++) {
////			chickens.remove(0);	// 치킨 거리 합이 가장 큰 치킨집 제거
////		}
//		///
//		
//		chickens = chickens.subList(0, M);	// 치킨 거리가 작은 M개만을 선별
//		
////		System.out.println();
////		for (int[] c : chickens) System.out.println(Arrays.toString(c));
////		System.out.println();
//		
//		int answer = 0;
//		
//		visited = new boolean[N][N];
//		for (int[] xy : houses) {
//			int x = xy[0], y = xy[1];
//			int minIdx = 0;
//			int minDist = Integer.MAX_VALUE;
//			for (int i = 0; i < chickens.size(); i++) {
//				int[] chicken = chickens.get(i);
//				int dist = Math.abs(chicken[0] - x) + Math.abs(chicken[1] - y);
//				if (dist < minDist) {
//					minDist = dist;
//					minIdx = i;
//				}
//			}
//			chickens.get(minIdx)[2] = minDist;
//			answer += minDist;
//			visited[x][y] = true;
//		}
//		
//		System.out.println(answer);
//	}
//}
