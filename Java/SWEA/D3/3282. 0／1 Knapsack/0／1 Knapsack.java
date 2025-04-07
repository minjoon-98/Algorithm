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
            
            int[] V = new int[N];	// 부피
            int[] C = new int[N];	// 가치
            
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                V[i] = Integer.parseInt(st.nextToken());
                C[i] = Integer.parseInt(st.nextToken());    
            }

            int[] knapsack = new int[K+1];
            
            // 1차원 knapsack
            for (int i = 0; i < N; i++) {
				for (int k = K; k >= V[i]; k--) {
					if (V[i] <= k) {
						knapsack[k] = Math.max(knapsack[k], knapsack[k - V[i]] + C[i]);						
					}
					
				}
			}
            
            sb.append("#").append(tc).append(" ").append(knapsack[K]).append("\n");
        }
        
        System.out.println(sb.toString());
    }
}


/// 2차원 knapsack
//package a0407.homework;
//
//import java.io.*;
//import java.util.*;
//
//public class Solution_d3_3282_01Knapsack_서울_14반_김민준 {
//
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//        StringTokenizer st = null;
//        int T = Integer.parseInt(br.readLine());
//        
//        for (int tc = 1; tc <= T; tc++) {
//        	st = new StringTokenizer(br.readLine());
//            int N = Integer.parseInt(st.nextToken());
//            int K = Integer.parseInt(st.nextToken());
//            
//            int[] V = new int[N+1];	// 부피
//            int[] C = new int[N+1];	// 가치
//            
//            for (int i = 1; i <= N; i++) {
//                st = new StringTokenizer(br.readLine());
//                V[i] = Integer.parseInt(st.nextToken());
//                C[i] = Integer.parseInt(st.nextToken());    
//            }
//
//            int[][] knapsack = new int[N+1][K+1];
//            
//            for (int i = 1; i <= N; i++) {
//				for (int k = 1; k <= K; k++) {
//					if (V[i] <= k) {
//						knapsack[i][k] = Math.max(knapsack[i-1][k], knapsack[i-1][k - V[i]] + C[i]);						
//					} else {
//						knapsack[i][k] = knapsack[i-1][k];
//					}
//					
//				}
//			}
//            
//            sb.append("#").append(tc).append(" ").append(knapsack[N][K]).append("\n");
//        }
//        
//        System.out.println(sb.toString());
//    }
//}
//
