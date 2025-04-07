//package a0407.homework;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[] W = new int[N];	// 무게
        int[] C = new int[N];	// 가치
        
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	W[i] = Integer.parseInt(st.nextToken());
        	C[i] = Integer.parseInt(st.nextToken());    
        }
        
        int[] knapsack = new int[K+1];
        
        // 1차원 knapsack
        for (int i = 0; i < N; i++) {
        	for (int k = K; k >= W[i]; k--) {
        		if (W[i] <= k) {
        			knapsack[k] = Math.max(knapsack[k], knapsack[k - W[i]] + C[i]);						
        		}
        		
        	}
        }
        
        System.out.println(knapsack[K]);
    }
}