//package a0327.homework;

import java.io.*;
import java.util.*;

public class Solution {

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		long weight;

		public Edge(int from, int to, long weight) {
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

	static Edge[] edgeList;
	static int[] parents;
	static int V, E;
	
	static void make() {
		parents = new int[V];
		for (int i = 0; i < V; i++) {
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
	
	public static void main(String[] args)  throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			edgeList = new Edge[E];
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken()) - 1;	// 0-base
				int to = Integer.parseInt(st.nextToken()) - 1;	// 0-base
				long weight = Long.parseLong(st.nextToken());
				edgeList[i] = new Edge(from, to, weight);
			}
			
			Arrays.sort(edgeList);
			make();	// V개의 독립된 트리 생성
			long result = 0;
			int count = 0;
			for (Edge edge : edgeList) {
				if (union(edge.from, edge.to)) {
					result += edge.weight;
					if (++count == V-1) {
						break;
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
