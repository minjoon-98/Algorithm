//package a0305.homework;

import java.io.*;
import java.util.*;

public class Solution {
	
	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	
	static int H, W;
	static char[][] map;
	static int r, c, d;
	
	static void move(int direction) {
		d = direction;
		int nx = r + dx[d], ny = c + dy[d];
		
		if (nx>=0 && nx<H && ny>=0 && ny<W && map[nx][ny] == '.') {
			map[r][c] = '.';
			r = nx;
			c = ny;
		}
		switch (d) {
		case 0:
			map[r][c] = '^';
			break;
		case 1:
			map[r][c] = 'v';
			break;
		case 2:
			map[r][c] = '<';
			break;
		case 3:
			map[r][c] = '>';
			break;
		default:
			break;
		}
		return;
	}
	
	static void shoot() {
		int nx = r + dx[d], ny = c + dy[d];
		while (nx>=0 && nx<H && ny>=0 && ny<W) {
			if (map[nx][ny] == '*') {
				map[nx][ny] = '.';
				return;
			} else if (map[nx][ny] == '#') {
				return;
			}
			nx = nx + dx[d];
			ny = ny + dy[d];
		}
		return;
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			map = new char[H][W];
			
			r = 0;
			c = 0;
			d = 0;
			
			for (int i = 0; i < H; i++) {
				String temp = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = temp.charAt(j);
					switch (map[i][j]) {
					case '^':
						r = i;
						c = j;
						d = 0;
						break;
					case 'v':
						r = i;
						c = j;
						d = 1;
						break;
					case '<':
						r = i;
						c = j;
						d = 2;
						break;
					case '>':
						r = i;
						c = j;
						d = 3;						
						break;
					default:
						break;
					}
				}
			}
			
			int N = Integer.parseInt(br.readLine());
			String commands = br.readLine();
//			char[] commands = new char[N];
//			st = new StringTokenizer(br.readLine());
//			for (int i = 0; i < N; i++) {
//				commands[i] = st.nextToken().charAt(0);
//			}
			for (int i = 0; i < N; i++) {
				switch (commands.charAt(i)) {
				case 'U':
					move(0);
					break;
				case 'D':
					move(1);
					break;
				case 'L':
					move(2);
					break;
				case 'R':
					move(3);
					break;
				case 'S':
					shoot();
					break;
				default:
					break;
				}
			}
			sb.append("#").append(tc).append(" ");
			
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			
		}
		
		System.out.println(sb.toString());
	}
	
}