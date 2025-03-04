//package a0304.homework;

import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			String command = st.nextToken();
			int[][] map = new int[N][N];
			int[][] result = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			switch (command) {
			case "left":
				for (int i = 0; i < N; i++) {
					ArrayDeque<int[]> stack = new ArrayDeque<>();
					for (int j = 0; j < N; j++) {
						if (!stack.isEmpty() && stack.peek()[1] == 0 && stack.peek()[0] == map[i][j]) {
							stack.push(new int[] {stack.pop()[0] + map[i][j], 1});
						} else if (map[i][j] != 0) {								
							stack.push(new int[] {map[i][j], 0});
						}
					}
					int s = 0;
					while (!stack.isEmpty()) {
						result[i][s++] = stack.pollLast()[0];
					}
				}
				break;
			case "right":
				for (int i = 0; i < N; i++) {
					ArrayDeque<int[]> stack = new ArrayDeque<>();
					for (int j = N-1; j >= 0; j--) {
						if (!stack.isEmpty() && stack.peek()[1] == 0 && stack.peek()[0] == map[i][j]) {
							stack.push(new int[] {stack.pop()[0] + map[i][j], 1});
						} else if (map[i][j] != 0) {								
							stack.push(new int[] {map[i][j], 0});
						}
					}
					int s = N;
					while (!stack.isEmpty()) {
						result[i][--s] = stack.pollLast()[0];
					}
				}				
				break;
			case "up":
				for (int j = 0; j < N; j++) {
					ArrayDeque<int[]> stack = new ArrayDeque<>();
					for (int i = 0; i < N; i++) {
						if (!stack.isEmpty() && stack.peek()[1] == 0 && stack.peek()[0] == map[i][j]) {
							stack.push(new int[] {stack.pop()[0] + map[i][j], 1});
						} else if (map[i][j] != 0) {								
							stack.push(new int[] {map[i][j], 0});
						}
					}
					int s = 0;
					while (!stack.isEmpty()) {
						result[s++][j] = stack.pollLast()[0];
					}
				}
				
				
				break;
			case "down":
				for (int j = 0; j < N; j++) {
					ArrayDeque<int[]> stack = new ArrayDeque<>();
					for (int i = N-1; i >= 0; i--) {
						if (!stack.isEmpty() && stack.peek()[1] == 0 && stack.peek()[0] == map[i][j]) {
							stack.push(new int[] {stack.pop()[0] + map[i][j], 1});
						} else if (map[i][j] != 0) {								
							stack.push(new int[] {map[i][j], 0});
						}
					}
					int s = N;
					while (!stack.isEmpty()) {
						result[--s][j] = stack.pollLast()[0];
					}
				}
				break;
			default:
				break;
			}
			
			sb.append("#").append(tc).append("\n");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(result[i][j]).append(" ");
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
	
}