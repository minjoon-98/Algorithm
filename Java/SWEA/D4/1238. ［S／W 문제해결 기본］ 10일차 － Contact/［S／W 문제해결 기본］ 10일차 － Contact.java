//package a0326.homework;

import java.io.*;
import java.util.*;

public class Solution {

	static List<Integer>[] graph;
	static boolean[] visited;
	
	static int bfs(int start) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {start, 0});
		visited[start] = true;
		
		int maxDepth = 0;
		int maxNum = 0;
		
		while (!q.isEmpty()) {
			int[] node = q.poll();
			if (node[1] == maxDepth) {
				maxNum = Math.max(maxNum, node[0]);
			} else if (node[1] > maxDepth) {
				maxDepth = node[1];
				maxNum = node[0];
			}
			for (int n : graph[node[0]]) {
				if (!visited[n]) {
					q.offer(new int[] {n, node[1] + 1});
					visited[n] = true;
				}
			}
		}
		return maxNum;
	}
	
	public static void main(String[] args)  throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = 10;
		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			int cmdLen = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());

			graph = new List[101];
			for (int i = 0; i < 101; i++) {
				graph[i] = new ArrayList<>();
			}
			visited = new boolean[101];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < cmdLen/2; i++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				graph[a].add(b);
			}
			
			int answer = bfs(start);
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
