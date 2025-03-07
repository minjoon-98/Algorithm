//package a0307.homework;

import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args)  throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		for (int tc = 1; tc <= 10; tc++) {

			sb.append("#").append(tc).append(" ");
			
			st = new StringTokenizer(br.readLine(), " ");
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			List<Integer>[] graph = new List[V];
			for (int i = 0; i < V; i++) {
				graph[i] = new ArrayList<>();
			}
			int[] indegree = new int[V];
//			int[] outdegree = new int[V];
			
			st = new StringTokenizer(br.readLine(), " "); 
			for (int i = 0; i < E; i++) {
				int from = Integer.parseInt(st.nextToken()) - 1;	// 0-base
				int to = Integer.parseInt(st.nextToken()) - 1;	// 0-base
				graph[from].add(to);
				++indegree[to];
//				++outdegree[from];
			}
			
			Queue<Integer> q = new ArrayDeque<>();
			
			for (int i = 0; i < V; i++) {
				if (indegree[i] == 0) {
					q.offer(i);
				}
			}
			
			while (!q.isEmpty()) {
				int current = q.poll();
				sb.append(current+1).append(" ");
				
				for (int next : graph[current]) {
					--indegree[next];
					if (indegree[next] == 0) {
						q.offer(next);
					}
				}
				
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
