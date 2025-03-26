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
			sb.append("#").append(tc).append(" ");

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			parents = new int[N+1];
			
			for (int i = 1; i < N+1; i++) {
				parents[i] = i;
			}
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int cmd = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				switch (cmd) {
				case 0:
					union(a, b);
					break;
				case 1:
					if (find(a) == find(b)) {
						sb.append(1);
					} else {
						sb.append(0);
					}
					break;
				default:
					break;
				}
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
