//package a0220.homework;

import java.io.*;
import java.util.*;

public class Solution {
	
	static int[][] map;
	
	static int deadFlies(int x, int y, int range) {
		int sum = 0;
		
		for (int i = x; i < x + range; i++) {
			for (int j = y; j < y + range; j++) {
				sum += map[i][j];
			}
		}
		
		return sum;
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int answer = 0;
			for (int i = 0; i <= N-M; i++) {
				for (int j = 0; j <= N-M; j++) {					
					answer = Math.max(answer, deadFlies(i, j, M));				
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

}
