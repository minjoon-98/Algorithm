//package a0407.homework;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
        	st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            
            int[] V = new int[N+1];	// 부피
            int[] C = new int[N+1];	// 가치
            
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                V[i] = Integer.parseInt(st.nextToken());
                C[i] = Integer.parseInt(st.nextToken());    
            }

            int[][] knapsack = new int[N+1][K+1];
            
            for (int i = 1; i <= N; i++) {
				for (int k = 1; k <= K; k++) {
					if (V[i] <= k) {
						knapsack[i][k] = Math.max(knapsack[i-1][k], knapsack[i-1][k - V[i]] + C[i]);						
					} else {
						knapsack[i][k] = knapsack[i-1][k];
					}
					
				}
			}
            
            sb.append("#").append(tc).append(" ").append(knapsack[N][K]).append("\n");
        }
        
        System.out.println(sb.toString());
    }
}

