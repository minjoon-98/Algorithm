//package a0326.homework;

import java.io.*;
import java.util.*;

public class Solution {

	static int N, M;
	static int[] parents;
	
	static int find(int a) {
		if (a == parents[a]) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args)  throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			parents = new int[N+1];
			
			for (int i = 1; i < N+1; i++) {
				parents[i] = i;
			}
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				union(a, b);
			}
			
			boolean[] greatParent = new boolean[N+1];
			int count = 0;
			for (int i = 1; i < N+1; i++) {
				int p = find(i);
				if (!greatParent[p]) {
					greatParent[p] = true;
					++count;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(count).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
