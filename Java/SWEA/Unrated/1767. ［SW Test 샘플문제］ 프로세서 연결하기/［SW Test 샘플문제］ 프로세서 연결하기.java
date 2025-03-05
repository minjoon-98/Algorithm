//package a0228.homework;

import java.io.*;
import java.util.*;

public class Solution {
    
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    static int N, maxCore, minWire;
    static int[][] map;
    static List<int[]> cores;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            cores = new ArrayList<>();
            
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    // 연결해야하는 코어들 (가장자리 제외)
                    if (map[i][j] == 1 && i > 0 && i < N - 1 && j > 0 && j < N - 1) {
                        cores.add(new int[]{i, j});
                    }
                }
            }
            
            maxCore = 0;
            minWire = Integer.MAX_VALUE;
            dfs(0, 0, 0);
            
            sb.append("#").append(tc).append(" ").append(minWire).append("\n");
        }
        System.out.println(sb);
    }
    
    static void dfs(int index, int connected, int wireLength) {
        // 가지치기 (현재 연결 가능한 코어 개수가 maxCore보다 작으면 탐색 중단)
        if (connected + (cores.size() - index) < maxCore) return;
        
        if (index == cores.size()) {
            if (connected > maxCore) {
                maxCore = connected;
                minWire = wireLength;
            } else if (connected == maxCore) {
                minWire = Math.min(minWire, wireLength);
            }
            return;
        }
        
        int[] core = cores.get(index);
        int x = core[0], y = core[1];
        
        // 4방향 탐색
        for (int d = 0; d < 4; d++) {
            int len = canPlaceWire(x, y, d);
            
            if (len > 0) { // 전선을 놓을 수 있으면
                placeWire(x, y, d, len, 2); // 전선 배치
                dfs(index + 1, connected + 1, wireLength + len);
                placeWire(x, y, d, len, 0); // 롤백
            }
        }
        
        // 전선을 연결하지 않는 경우도 탐색
        dfs(index + 1, connected, wireLength);
    }

    // 전선 설치 가능 여부 체크 (가능하면 전선 길이 반환, 불가능하면 0 반환)
    static int canPlaceWire(int x, int y, int d) {
        int nx = x + dx[d], ny = y + dy[d], len = 0;
        while (nx >= 0 && nx < N && ny >= 0 && ny < N) {
            if (map[nx][ny] != 0) return 0;
            nx += dx[d];
            ny += dy[d];
            len++;
        }
        return len;
    }

    // 전선 설치 및 제거 (전선 설치 시 value = 2, 제거 시 value = 0)
    static void placeWire(int x, int y, int d, int len, int value) {
        int nx = x + dx[d], ny = y + dy[d];
        while (len-- > 0) {
            map[nx][ny] = value;
            nx += dx[d];
            ny += dy[d];
        }
    }
}


///// 무한 루프
///// 가지치기를 더욱 더 해야함
//package a0228.homework;
//
//import java.io.*;
//import java.util.*;
//
//public class Solution_ur_프로세서연결하기_서울_14반_김민준 {
//	
//	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
//	
//	static int N;
//	static int[][] map;
//	static int[] answer;
//
//	static void wiring(int index, int connected, int wireLength, List<int[]> cores, int coreCnt) {
//		
//		if (index == coreCnt) {
//			if (answer[0] <= connected) {
//				answer[1] = Math.max(answer[1], wireLength);
//			}
//			return;
//		}
//		
//		for (int i = index; i < coreCnt; i++) {
//			for (int d = 0; d < 4; d++) {
//				
//				int[] xy = cores.get(i);
//				int nx = xy[0] + dx[d], ny = xy[1] + dy[d];
//				
//				boolean reachable = true;
//				while (nx>=0 && nx<N && ny>=0 && ny<N) {
//					if (map[nx][ny] != 0) {
//						reachable = false;
//						break;
//					}
//					nx = nx + dx[d];
//					ny = ny + dy[d];					
//				}
//				
//				if (reachable) {
//					int length = 0;
//					while (nx>=0 && nx<N && ny>=0 && ny<N) {
//						map[nx][ny] = 2;
//						++length;
//						nx = nx + dx[d];
//						ny = ny + dy[d];					
//					}
//					wiring(index+1, connected+1, wireLength + length, cores, coreCnt);
//					while (nx>=0 && nx<N && ny>=0 && ny<N) {
//						map[nx][ny] = 0;
//						--length;
//						nx = nx - dx[d];
//						ny = ny - dy[d];					
//					}
//				} else {
//					wiring(index+1, connected, wireLength, cores, coreCnt);
//				}
//				
//			}
//		}
//		
//	}
//	
//	
//	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = null;
//		StringBuilder sb = new StringBuilder();
//		
//		int T = Integer.parseInt(br.readLine());
//		
//		for (int tc = 1; tc <= T; tc++) {
//			
//			N = Integer.parseInt(br.readLine());
//			map = new int[N][N];
//			List<int[]> cores = new ArrayList<>();
//			answer = new int[] {0, 0};
//			
//			for (int i = 0; i < N; i++) {
//				st = new StringTokenizer(br.readLine());
//				for (int j = 0; j < N; j++) {
//					map[i][j] = Integer.parseInt(st.nextToken());
//					if (map[i][j] == 1) {
//						if (i==0 || i == N-1 || j == 0 || j == N-1) {
////							cores.add(new int[] {i, j, 1});	// 이미 연결된 경우 (가장자리 위치)
//							++answer[0];
//						} else {
//							cores.add(new int[] {i, j, 0});
//						}
//					}
//				}
//			}
//			
//			wiring(0, answer[0], 0, cores, cores.size());
//	
//			sb.append("#").append(tc).append(" ").append(answer).append("\n");
//		}
//		
//		System.out.println(sb.toString());
//	}
//	
//}