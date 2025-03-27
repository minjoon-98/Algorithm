//package a0327.homework;

import java.io.*;
import java.util.*;

public class Solution {

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		double weight;

		public Edge(int from, int to, double weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}

	}

	static ArrayList<Edge> edgeList;
	static int[] parents;
	static int N;
	static double E;
	static int[][] islands;
	
	static void make() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if (a == parents[a]) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot) return false;
//		parents[bRoot] = aRoot
		// 위 방법에 비해서는 한쪽으로 기우는 거 방지 (랜덤 요소)
		else if (aRoot > bRoot) parents[bRoot] = aRoot;
		else parents[aRoot] = bRoot;
		return true;
	}
	
//	// 조합 (메모리 초과)
//	static void comb(int idx, int cnt, List<Integer> selected) {
//		
//		if (cnt == 2) {
//			int from = selected.get(0);
//			int to = selected.get(1);
//			double weight = Math.hypot(islands[from][0] - islands[to][0], islands[from][1] - islands[to][1]) * E;
//			Edge edge = new Edge(from, to, weight);
//			edgeList.add(edge);
//			return;
//		}
//		
//		if (idx >= N) return;
//		
//		for (int i = idx; i < N; i++) {
//			selected.add(Integer.valueOf(i));	// add는 그냥 i로 해도 무관
//			comb(idx+1, cnt+1, selected);
//			selected.remove(Integer.valueOf(i));	// 그냥 i로 하면 index로 취급해서 해당 위치를 참조함
//			comb(idx+1, cnt, selected);
//		}
//	}
	
	public static void main(String[] args)  throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			islands = new int[N][2];
			
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					islands[j][i] = Integer.parseInt(st.nextToken());
				}
			}
			
			E = Double.parseDouble(br.readLine());

			edgeList = new ArrayList<>();
//			comb(0, 0, new ArrayList<>());
			// 간선 리스트 생성 (모든 섬 간의 간선 생성)
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    double weight = Math.pow(Math.hypot(islands[i][0] - islands[j][0], islands[i][1] - islands[j][1]), 2) * E;
                    edgeList.add(new Edge(i, j, weight));
                }
            }
			
			Collections.sort(edgeList);
			
			make();	// V개의 독립된 트리 생성
			double result = 0;
			int count = 0;
			for (Edge edge : edgeList) {
				if (union(edge.from, edge.to)) {
					result += edge.weight;
					if (++count == N-1) {
						break;
					}
				}
			}
			
			long answer = (long) Math.round(result);
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
