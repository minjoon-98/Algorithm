//package a0226.homework;

import java.io.*;
import java.util.*;

public class Solution {
	
	static int B;
	static int[] heights;
	
	static int select(int index, int sum) {
		if (index <= 0) {
			return sum > B ? sum - B : Integer.MAX_VALUE;
		}
		if (sum >= B) {
			return sum - B;
		}
		return Math.min(select(index - 1, sum + heights[index - 1]), select(index - 1, sum));
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
				
		for (int tc = 1; tc <= T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			heights = new int[N];
			
			int S = 0;
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				heights[i] = Integer.parseInt(st.nextToken());
				S += heights[i];
			}
			
			int sub = Math.abs(S - B);
			Arrays.sort(heights);
			
			int answer = select(N, 0);
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.println(sb.toString());
	}

}