//package a0404.homework;

import java.io.*;
import java.util.*;

public class Main {
	
//	static int N;
//	static int[][] map;
	
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());		
		
		int[][] map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][][] dp = new int[3][N][N];	// 0:→,  1:↘, 2:↓ 방향의 파이프 상태에 따른 DP 테이블
		dp[0][0][1] = 1;
		
		for (int j = 2; j < N; j++) {
			if (map[0][j] != 1) {
				dp[0][0][j] = dp[0][0][j-1];
			}
		}
		
		for (int j = 2; j < N; j++) {
			if (map[1][j] != 1 && map[0][j] != 1 && map[1][j-1] != 1) {
				dp[1][1][j] = dp[0][0][j-1];
			}
		}
		
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < N; j++) {
				
				/// 올 수 있는 모든 경우
//				// 가로로 올 수 있는 경우
//				dp[0][i][j] = dp[0][i][j-1] + dp[1][i][j-1];
//				// 대각선으로 올 수 있는 경우
//				dp[1][i][j] = dp[0][i-1][j-1] + dp[1][i-1][j-1] + dp[2][i-1][j-1];
//				// 세로로 올 수 있는 경우
//				dp[2][i][j] = dp[1][i-1][j] + dp[2][i-1][j];
				
				if (map[i][j] != 1) {
					dp[0][i][j] = dp[0][i][j-1] + dp[1][i][j-1];	// 가로로 올 수 있는 경우
					dp[2][i][j] = dp[1][i-1][j] + dp[2][i-1][j];	// 세로로 올 수 있는 경우
					if (map[i-1][j] != 1 && map[i][j-1] != 1) {
						dp[1][i][j] = dp[0][i-1][j-1] + dp[1][i-1][j-1] + dp[2][i-1][j-1];	// 대각선으로 올 수 있는 경우
					}
				} 
			}
		}
		
//		System.out.println("가로");
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(dp[0][i]));
//		}
//		System.out.println("대각선");
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(dp[1][i]));
//		}
//		System.out.println("세로");
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(dp[2][i]));
//		}
		
		System.out.println(dp[0][N-1][N-1] + dp[1][N-1][N-1] + dp[2][N-1][N-1]);
	}
}
