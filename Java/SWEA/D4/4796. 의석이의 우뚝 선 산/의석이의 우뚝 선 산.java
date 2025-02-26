//package a0226.homework;

import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
//		int T = Integer.parseInt(br.readLine());
		int T = sc.nextInt();
				
		for (int tc = 1; tc <= T; tc++) {
//			int N = Integer.parseInt(br.readLine());
			int N = sc.nextInt();
			int[] heights = new int[N];

//			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
//				heights[i] = Integer.parseInt(temp[i]);
				heights[i] = sc.nextInt();
			}
			
			
			int answer = 0;
			int pi = 0, pj = 1;
			int increaseCount = 0, decreaseCount = 0;
			
			while (pj < N) {
				// 1. 증가 구간 탐색
				while (pj < N && heights[pj - 1] < heights[pj]) {
					increaseCount++;
					pj++;
				}
				
				// 증가 구간이 없으면 넘어감
				if (increaseCount == 0) {
					pi = pj;
					pj++;
					continue;
				}
				
				// 2. 감소 구간 탐색
				decreaseCount = 0;
				while (pj < N && heights[pj - 1] > heights[pj]) {
					decreaseCount++;
					answer += increaseCount; // 증가 구간 수만큼 감소 구간을 선택할 수 있음.
					pj++;
				}
				
				// 3. 감소 구간이 없으면 초기화 후 다시 탐색
				if (decreaseCount == 0) {
					increaseCount = 0;
					pi = pj;
					pj++;
					continue;
				}
				
				// 4. 새로운 구간 탐색을 위해 초기화
				pi = pj - 1; // 감소 구간 끝에서 다시 탐색 시작
				increaseCount = 0;
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
}