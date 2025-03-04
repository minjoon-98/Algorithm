//package a0304.homework;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};	// 0:북, 1:동, 2:남, 3:서
	static int[][] map;
	static int count;
	
	static void robotCleaner(int r, int c, int d) {
		
		if (map[r][c] == 0) {
			map[r][c] = 2;	// 2: 청소 완료
			++count;
		}
		
		boolean uncleaned = false;
		for (int i = 0; i < 4; i++) {
			int nx = r + dx[i], ny = c + dy[i];
			if (nx>=0 && nx<N && ny>=0 && ny<M) {
				if (map[nx][ny] == 0) {
					uncleaned = true;
				}
			}
		}
		
		if (uncleaned) {	// 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
//			int nd = (d-1)%4;	// 반시계 방향 90도	// 이렇게 하면 음수 모듈러 연산이 잘못되는 경우가 생김
			int nd = (d+3)%4;	// 반시계 방향 90도
			int nx = r + dx[nd], ny = c + dy[nd];
			if (nx>=0 && nx<N && ny>=0 && ny<M) {
				if (map[nx][ny] == 0) {
					robotCleaner(nx, ny, nd);					
				} else {
					robotCleaner(r, c, nd);
				}
			}			
		} else {	// 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
			int nd = (d+2)%4;	// 후진 방향
			int nx = r + dx[nd], ny = c + dy[nd];
			if (nx>=0 && nx<N && ny>=0 && ny<M) {
				if (map[nx][ny] == 1) {	// 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다
					return;
				} else {
					robotCleaner(nx, ny, d);
				}
			}
		}

		return;
	}
	
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		count = 0;
		robotCleaner(r, c, d);
		
		System.out.println(count);
	}
}
