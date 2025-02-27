//package a0227.homework;

import java.io.*;
import java.util.*;

public class Solution {
	
	static int[] passPrice;	// 1일, 1달, 3달, 1년 이용권 요금 
	static int[] monthlyPlan;	// 월별 이용 계획
	static int answer;
	
	static void backtracking(int month, int cost) {
		if (month > 11) {
			answer = Math.min(answer, cost);
			return;
		}
		
		// 3달 이용권 사용
		backtracking(month + 3, cost + passPrice[2]);
		
		// 1달 이용권 사용
		backtracking(month + 1, cost + passPrice[1]);
				
		// 1일 이용권 사용
		backtracking(month + 1, cost + passPrice[0] * monthlyPlan[month]);
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			passPrice = new int[4];
			for (int i = 0; i < 4; i++) {
				passPrice[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			monthlyPlan = new int[12];
			for (int i = 0; i < 12; i++) {
				monthlyPlan[i] = Integer.parseInt(st.nextToken());
			}
			
			answer = passPrice[3];	// 1년 이용권 요금을 기본으로 잡고 시작
			
			backtracking(0, 0);
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
}