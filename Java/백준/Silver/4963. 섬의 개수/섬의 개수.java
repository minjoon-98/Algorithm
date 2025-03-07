// FloodFill

import java.io.*;
import java.util.*;

public class Main {
	
	private static int R, C, map[][];
	
	private static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	private static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	private static void dfs(int r, int c) {

		map[r][c] = 0; // 탐색한것으로 바꾸기
		//8방 탐색
		for (int d = 0; d < 8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr<0 || nr>=R || nc<0 || nc>=C) continue; // 경계 벗어나면 skip
			if (map[nr][nc] == 0) continue; // 바다이거나 탐색된 땅이면 skip
			
			dfs(nr, nc);
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
//		int T = Integer.parseInt(br.readLine());
		
		boolean end = false;
		
//		for (int tc = 0; tc < T; tc++) {
		while (!end) {
			st = new StringTokenizer(br.readLine(), " ");
			C = Integer.parseInt(st.nextToken()); 
			R = Integer.parseInt(st.nextToken());
			
			if (R == 0 && C == 0) {
				end = true;
				break;
			}

			map = new int[R][C];
			
			int cnt = 0; // 섬 개수 카운팅
			
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < C; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 탐색되지 않은 땅을 시작점으로 섬 찾기
			// map을 땅(1), 바다(0), 탐색된 섬(2이상의 값, 재탐색 방지용으로 0)으로 기록해서 사용
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					// 탐색되지 않은 땅을 발견 ==> 새로운 섬 시작 발견
					if (map[i][j] == 1) {
						++cnt;
						dfs(i, j);
					}
				}
			}
			
//			sb.append("#").append(tc).append(" ").append(cnt).append("\n");
			sb.append(cnt).append("\n");
		}
		System.out.println(sb.toString());
	}

}

/*

6
1 1
0
2 2
0 1
1 0
3 2
1 1 1
1 1 1
5 4
1 0 1 0 0
1 0 0 0 0
1 0 1 0 1
1 0 0 1 0
5 4
1 1 1 0 1
1 0 1 0 1
1 0 1 0 1
1 0 1 1 1
5 5
1 0 1 0 1
0 0 0 0 0
1 0 1 0 1
0 0 0 0 0
1 0 1 0 1


#1 0
#2 1
#3 1
#4 3
#5 1
#6 9

*/
