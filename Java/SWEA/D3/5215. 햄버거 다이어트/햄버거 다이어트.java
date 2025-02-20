//package a0220.homework;

import java.io.*;
import java.util.*;

public class Solution {
	
	static int N, L;
	static int[][] ingredients;
	static boolean[] visited;
	static int maxScore;
	
	static void makeBurger(int index, int totalScore, int totalCalories) {
		
		if (totalCalories > L) {
			return;
		}
		maxScore = Math.max(maxScore, totalScore);
		if (index == N) {
			return;
		}
		
		makeBurger(index + 1, totalScore + ingredients[index][0], totalCalories + ingredients[index][1]);

		makeBurger(index + 1, totalScore, totalCalories);
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			ingredients = new int[N][2];
			visited = new boolean[N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				ingredients[i][0] = Integer.parseInt(st.nextToken());
				ingredients[i][1] = Integer.parseInt(st.nextToken());
			}
			
			maxScore = 0;
			makeBurger(0, 0, 0);
			
			sb.append("#").append(tc).append(" ").append(maxScore).append("\n");
		}

		System.out.println(sb.toString());
	}

}
