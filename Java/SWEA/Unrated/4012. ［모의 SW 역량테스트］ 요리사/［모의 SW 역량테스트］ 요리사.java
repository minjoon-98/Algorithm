import java.io.*;
import java.util.*;

public class Solution {
	
	static int N;
	static int[][] synergy;
	static int[] isSelected;
	
	static void swap(int i, int j) {
		int temp = isSelected[i];
		isSelected[i] = isSelected[j];
		isSelected[j] = temp;
	}
	
	static boolean nPn() {
		int i = N-1;
		while (i>0 && isSelected[i-1] >= isSelected[i]) i--;
		if (i == 0) return false;
		
		int j = N-1;
		while (isSelected[i-1] >= isSelected[j]) j--;
		swap(i-1, j);
		
		int k = N-1;
		while (i<k) swap(i++, k--);
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
				
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			synergy = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					synergy[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			isSelected = new int[N];
			for (int i = N/2; i < N; i++) {
				isSelected[i] = 1;
			}
			
			int answer = Integer.MAX_VALUE;
			
			do {
				int score1 = 0, score2 = 0;
				
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (isSelected[i] == 0 && isSelected[j] == 0) {
							score1 += synergy[i][j];
						} else if (isSelected[i] == 1 && isSelected[j] == 1) {
							score2 += synergy[i][j];							
						}
					}
				}
				answer = Math.min(answer, Math.abs(score1 - score2));
			} while (nPn());
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.println(sb.toString());
	}

}
