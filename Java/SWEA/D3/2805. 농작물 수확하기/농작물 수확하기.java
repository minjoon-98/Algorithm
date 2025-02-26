//package a0226.homework;

import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
				
		for (int tc = 1; tc <= T; tc++) {
			
			int N = Integer.parseInt(br.readLine());
//			int[][] map = new int[N][N];
			
			int center = N/2;
			
			int answer = 0;
			for (int i = 0; i < N; i++) {
				String temp = br.readLine();
				for (int j = 0; j < N; j++) {
//					map[i][j] = Character.getNumericValue(temp.charAt(j));
					if (Math.abs(center-i) + Math.abs(center-j) <= center) {
						answer += Character.getNumericValue(temp.charAt(j));
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.println(sb.toString());
	}

}