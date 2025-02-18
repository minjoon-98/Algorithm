import java.util.*;
import java.io.*;

class Main {

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
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());	// 학생 수
		int M = Integer.parseInt(st.nextToken());	// 비교 횟수
		
		graph = new ArrayList<>();
		graphR = new ArrayList<>();
		
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
			graphR.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(b); // 순방향 그래프 (a → b)
			graphR.get(b).add(a); // 역방향 그래프 (b → a)
			
		}
		
		int answer = 0;
		for (int i = 1; i <= N; i++) {
			int count = bfs(i, N, graph) + bfs(i, N, graphR);	// 나보다 작은 애들 + 나보다 큰 애들
			if (count == N-1) {	// 내 순서가 확정되었을 때
				answer++;
			}
		}		
		System.out.println(answer);
	}
}
