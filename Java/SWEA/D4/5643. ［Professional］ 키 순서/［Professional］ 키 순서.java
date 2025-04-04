//package a0404.homework;

import java.io.*;
import java.util.*;

public class Solution {
	
	static int N;
	static List<Integer>[] graph;
	static List<Integer>[] graphR;
	static boolean[] visited;
    static boolean[] visitedR;
    
	static int bfs(int idx, boolean direction) {
		int count = 0;
		
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(idx);
		
		visited = new boolean[N+1];
        visitedR = new boolean[N+1];
		if (direction) visited[idx] = true;
		else visitedR[idx] = true;
		
		while (!q.isEmpty()) {
			int c = q.poll();
			if (direction) {
				for (int n : graph[c]) {
					if (visited[n]) continue;
					q.offer(n);
					visited[n] = true;
					count++;
				}
			} else {
				for (int n : graphR[c]) {
					if (visitedR[n]) continue;
					q.offer(n);
					visitedR[n] = true;
					count++;
				}				
			}
		}
		
		return count;
	}

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());
            
            graph = new List[N+1];	// 1-base
            graphR = new List[N+1];	// 1-base
            for (int i = 0; i < N+1; i++) {
				graph[i] = new ArrayList<>();
				graphR[i] = new ArrayList<>();
			}
                        
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                
                graph[a].add(b);
                graphR[b].add(a);
            }
            
            int answer = 0;
            for (int i = 1; i < N + 1; i++) {
				if (bfs(i, true) + bfs(i, false) == N - 1) {
					++answer;
				}
			}
            
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        
        System.out.println(sb.toString());
    }
}

