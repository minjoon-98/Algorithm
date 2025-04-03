//package a0403.homework;

import java.io.*;
import java.util.*;

public class Solution {
    static int N, K, answer;
    static int[][] map;
    static boolean[][] visited;
    // 상하좌우 이동
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    // DFS 함수: (x, y)에서 현재까지의 경로 길이 len, 아직 깎기 가능 여부 excav,
    // 현재 칸의 유효 높이 curHeight를 인자로 받는다.
    static void dfs(int x, int y, int len, boolean excav, int curHeight) {
        answer = Math.max(answer, len);
        visited[x][y] = true; // 현재 칸 방문 처리
        
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d], ny = y + dy[d];
            // 범위 및 방문 여부 확인
            if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;
            
            // 깎지 않고 단순 이동: 현재 칸의 유효 높이보다 인접 칸의 원래 높이가 낮은 경우
            if (curHeight > map[nx][ny]) {
                dfs(nx, ny, len + 1, excav, map[nx][ny]);
            }
            // 깎기를 사용해서 이동할 경우: 아직 깎기를 사용하지 않았고, 인접 칸을 (curHeight - 1)로 낮출 수 있는 경우
            else if (excav && curHeight > map[nx][ny] - K) {
                int original = map[nx][ny];
                // 실제로 깎아서 curHeight-1보다 낮게 만들 수 있어야 함 (즉, 깎을 필요가 있는 경우)
                if (curHeight - 1 < original) {
                    map[nx][ny] = curHeight - 1;  // 필요한 만큼만 깎음 (적게 깎아야 최대한 길게 갈 수 있음)
                    dfs(nx, ny, len + 1, false, map[nx][ny]);
                    map[nx][ny] = original; // 백트래킹: 원래 높이 복원
                }
            }
        }
        visited[x][y] = false; // 다른 경로를 위해 방문 상태 해제
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            answer = 0;
            int max = 0;
            
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    max = Math.max(max, map[i][j]);
                }
            }
            
            visited = new boolean[N][N];
            
            // 가장 높은 봉우리에서 시작해서 DFS 수행
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == max) {
                        dfs(i, j, 1, true, map[i][j]);
                    }
                }
            }
            
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        
        System.out.println(sb.toString());
    }
}


/// BFS + 비트마스킹
//package a0403.homework;
//
//import java.io.*;
//import java.util.*;
//
//public class Solution_ur_1949_등산로조성_서울_14반_김민준 {
//    static int N, K;
//    static int[][] graph;
//    // 상하좌우 이동
//    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
//    static int answer;
//    
//    // 방문 상태는 비트마스크(최대 8x8=64칸)로 관리
//    // 각 경로마다 방문한 칸을 기록하여 사이클(같은 칸 재방문)을 방지한다.
//    static class State {
//        int x, y, len, excav, h; // 현재 좌표, 경로 길이, 깎기 사용 가능 여부, 현재 칸의 유효 높이
//        long visited;            // 비트마스크: 현재 경로에서 방문한 칸
//        public State(int x, int y, int len, int excav, int h, long visited) {
//            this.x = x;
//            this.y = y;
//            this.len = len;
//            this.excav = excav;
//            this.h = h;
//            this.visited = visited;
//        }
//    }
//    
//    static void bfs(int i, int j) {
//        Queue<State> q = new ArrayDeque<>();
//        // 시작 위치의 방문 마스크: (i, j)에 해당하는 비트를 1로 설정
//        long startVisited = 1L << (i * N + j);
//        q.offer(new State(i, j, 1, 1, graph[i][j], startVisited));
//        
//        while (!q.isEmpty()) {
//            State cur = q.poll();
//            answer = Math.max(answer, cur.len);
//            
//            for (int d = 0; d < 4; d++) {
//                int nx = cur.x + dx[d], ny = cur.y + dy[d];
//                // 범위 체크
//                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
//                // 이미 현재 경로에서 방문한 칸이면 스킵(사이클 방지)
//                if ((cur.visited & (1L << (nx * N + ny))) != 0) continue;
//                
//                // 새 방문 마스크 생성
//                long newVisited = cur.visited | (1L << (nx * N + ny));
//                
//                // 일반적으로 이동 가능한 경우: 현재 칸의 유효 높이(cur.h)가 인접 칸의 원래 높이보다 높을 때
//                if (cur.h > graph[nx][ny]) {
//                    q.offer(new State(nx, ny, cur.len + 1, cur.excav, graph[nx][ny], newVisited));
//                } 
//                // 깎기를 사용해서 이동할 경우: 아직 깎기를 사용하지 않았으며(cur.excav==1)
//                // 인접 칸의 높이를 (cur.h - 1)로 깎을 수 있는지 체크 (깎은 양이 K 이하)
//                else if (cur.excav == 1 && graph[nx][ny] - (cur.h - 1) <= K && (cur.h - 1) < graph[nx][ny]) {
//                    q.offer(new State(nx, ny, cur.len + 1, 0, cur.h - 1, newVisited));
//                }
//            }
//        }
//    }
//    
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//        int T = Integer.parseInt(br.readLine());
//        
//        for (int tc = 1; tc <= T; tc++) {
//            StringTokenizer st = new StringTokenizer(br.readLine());
//            N = Integer.parseInt(st.nextToken());
//            K = Integer.parseInt(st.nextToken());
//            
//            graph = new int[N][N];
//            int peak = 0;
//            for (int i = 0; i < N; i++) {
//                st = new StringTokenizer(br.readLine());
//                for (int j = 0; j < N; j++) {
//                    graph[i][j] = Integer.parseInt(st.nextToken());
//                    peak = Math.max(peak, graph[i][j]);
//                }
//            }
//            
//            // 가장 높은 봉우리를 찾는다.
//            List<int[]> peaks = new ArrayList<>();
//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < N; j++) {
//                    if (graph[i][j] == peak) {
//                        peaks.add(new int[] {i, j});
//                    }
//                }
//            }
//            
//            answer = 0;
//            // 각 봉우리마다 BFS 실행
//            for (int[] xy : peaks) {
//                bfs(xy[0], xy[1]);
//            }
//            
//            sb.append("#").append(tc).append(" ").append(answer).append("\n");
//        }
//        
//        System.out.println(sb.toString());
//    }
//}




/// BFS 코드	visited 없이 높이로만 할려고 했는데 이렇게하면 왔던 길을 다시 깎고 지나갈 수도 있음
//package a0403.homework;
//
//import java.io.*;
//import java.util.*;
//
//public class Solution_ur_1949_등산로조성_서울_14반_김민준 {
//    static int N, K;
//    static int[][] graph;
//    
//    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
//    static int answer;
//
//    static void bfs(int i, int j) {
//    	Queue<int[]> q = new ArrayDeque<>();
//    	q.offer(new int[] {i, j, 1, 1, -1, -1, 0});	// x, y, 길이, 깍을 수 있는 봉우리 수 : 1, 깎은 봉우리 좌표 (x, y), 깎은 직후인지 아닌지
//    	
//    	while (!q.isEmpty()) {
//			int[] xylk = q.poll();
//			int x = xylk[0];
//			int y = xylk[1];
//			int len = xylk[2];
//			int p = xylk[3];
//			int px = xylk[4];
//			int py = xylk[5];
//			int flag = xylk[6];
//			int height = 0;
//			
//			answer = Math.max(answer, len);
//			
//			if (flag == 1) {
//				height = graph[x][y] - K;
//			} else {
//				height = graph[x][y];
//			}
//			for (int d = 0; d < 4; d++) {
//				int nx = x + dx[d], ny = y + dy[d];
//				if (nx>=0 && nx<N && ny>=0 && ny<N) {
//					if (nx == px && ny == py) continue;
//					if (height > graph[nx][ny]) {
//						q.offer(new int[] {nx, ny, len+1, p, px, py, 0});						
//					} else if (p == 1 && height > graph[nx][ny] - K) {
//						q.offer(new int[] {nx, ny, len+1, p-1, x, y, 1});
//					}
//				}
//			}
//		}
//    	
//    }
//
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//        int T = Integer.parseInt(br.readLine());
//        
//        for (int tc = 1; tc <= T; tc++) {
//            StringTokenizer st = new StringTokenizer(br.readLine());
//            N = Integer.parseInt(st.nextToken());
//            K = Integer.parseInt(st.nextToken());
//            
//            graph = new int[N][N];
//            int peak = 0;
//            for (int i = 0; i < N; i++) {
//                st = new StringTokenizer(br.readLine());
//                for (int j = 0; j < N; j++) {
//                    graph[i][j] = Integer.parseInt(st.nextToken());
//                    peak = Math.max(peak, graph[i][j]);
//                }
//            }
//            
//            List<int[]> peaks = new ArrayList<>();
//            
//            for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					if (graph[i][j] == peak) {
//						peaks.add(new int[] {i, j});
//					}
//				}
//			}
//            
//            answer = 0;
//            
//            for (int[] xy : peaks) {
//				bfs(xy[0], xy[1]);
//			}
//            
//            sb.append("#").append(tc).append(" ").append(answer).append("\n");
//        }
//        
//        System.out.println(sb.toString());
//    }
//
//}
//
