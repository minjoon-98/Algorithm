//package aTest2;

import java.util.*;
import java.io.*;

class Solution {

	static ArrayList<ArrayList<Integer>> graph;
	static ArrayList<ArrayList<Integer>> graphR;
	
	static int bfs(int idx, int n, ArrayList<ArrayList<Integer>> g) {
		
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[n+1];
		
		q.offer(idx);
		visited[idx] = true;
		int count = 0;
		
		while (!q.isEmpty()) {
			int node = q.poll();
			for (int next : g.get(node)) {
				if (!visited[next]) {
					q.offer(next);
					visited[next] = true;
					count++;
				}
			}
		}
		
		return count;
	}
	
	public static void main(String args[]) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			
			graph = new ArrayList<>();
			graphR = new ArrayList<>();
			
			for (int i = 0; i <= N; i++) {
				graph.add(new ArrayList<>());
				graphR.add(new ArrayList<>());
			}
			
			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				graph.get(a).add(b);
				graphR.get(b).add(a);

			}
			
			int answer = 0;
			for (int i = 1; i <= N; i++) {
				int count = bfs(i, N, graph) + bfs(i, N, graphR);
				if (count == N-1) {
					answer++;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());		
	}
}
