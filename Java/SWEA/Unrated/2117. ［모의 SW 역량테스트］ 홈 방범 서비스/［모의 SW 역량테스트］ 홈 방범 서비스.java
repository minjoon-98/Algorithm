//package a0401.homework;

import java.io.*;
import java.util.*;

public class Solution {
	
	static int N, M;
	static int[][] graph;
	static int answer;
	
	static int service(int r, int c) {
//		int maxProfit = 0;
		int maxHouseCount = 0;
		
		for (int k = 1; k <= 2*N; k++) {
//			int profit = 0;
			int houseCount = 0;
			int cost = k*k + (k-1)*(k-1);

			for (int i = r - (k-1); i <= r + (k-1); i++) {
				for (int j = c - (k-1) ; j <= c + (k-1); j++) {
					if (i>=0 && i < N && j>=0 && j<N && Math.abs(r-i) + Math.abs(c-j) < k) {
						if (graph[i][j] == 1) {
//							profit += M;
							++houseCount;
						}
					}
				}
			}
//			if (profit >= cost) {					
			if (houseCount * M >= cost) {					
//				maxProfit = Math.max(maxProfit, profit - cost);
				maxHouseCount = Math.max(maxHouseCount, houseCount);
			}
		}
//		return maxProfit;
		return maxHouseCount;
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
			
			graph = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int profit = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					profit = Math.max(profit, service(i, j));
				}
			}
			
			sb.append("#").append(tc).append(" ").append(profit).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}