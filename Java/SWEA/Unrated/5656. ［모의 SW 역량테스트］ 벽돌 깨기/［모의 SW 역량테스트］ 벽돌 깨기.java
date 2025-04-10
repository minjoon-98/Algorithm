//package a0410.homework;

import java.io.*;
import java.util.*;

public class Solution {
    
    static int N, W, H;
    static int answer;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    // 개선된 deep copy - 각 행을 Arrays.copyOf로 복사
    static int[][] copyMap(int[][] original) {
        int[][] copied = new int[H][W];
        for (int i = 0; i < H; i++) {
            copied[i] = Arrays.copyOf(original[i], W);
        }
        return copied;
    }
    
    // 구슬 폭발 및 벽돌 내리기 함수
    static int[][] pop(int r, int c, int[][] map) {
        Queue<int[]> queue = new ArrayDeque<>();
        if(map[r][c] > 1) {
            queue.offer(new int[] {r, c, map[r][c]});
        }
        map[r][c] = 0; // 선택한 벽돌 제거
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int range = cur[2];
            
            for (int d = 0; d < 4; d++) {
                for (int k = 1; k < range; k++) {
                    int nx = x + dx[d] * k;
                    int ny = y + dy[d] * k;
                    
                    if (nx < 0 || nx >= H || ny < 0 || ny >= W) break;
                    if (map[nx][ny] == 0) continue;
                    
                    if (map[nx][ny] > 1) {
                        queue.offer(new int[] {nx, ny, map[nx][ny]});
                    }
                    map[nx][ny] = 0;
                }
            }
        }
        
        // 벽돌 내리기: 각 열별로 스택에 담아서 바닥부터 재배치
        for (int j = 0; j < W; j++) {
            Deque<Integer> stack = new ArrayDeque<>();
            for (int i = 0; i < H; i++) {
                if(map[i][j] != 0) {
                    stack.push(map[i][j]);
                    map[i][j] = 0;
                }
            }
            int idx = H - 1;
            while (!stack.isEmpty()) {
                map[idx--][j] = stack.pop();
            }
        }
        return map;
    }
    
    // 현재 보드에 남은 벽돌 수를 계산하는 함수
    static int getBrickCount(int[][] map) {
        int count = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] != 0) count++;
            }
        }
        return count;
    }
    
    static void permWithRep(int cnt, int[][] map) {
        // 남은 벽돌 수를 미리 계산해서 가지치기
        int remain = getBrickCount(map);
        if (remain == 0) { // 모든 벽돌 제거됨
            answer = 0;
            return;
        }
        // 현재까지의 결과보다 남은 벽돌 수가 많으면 더 이상 탐색할 필요가 없음
        answer = Math.min(answer, remain);
        if (cnt == N) return;
        
        // 이미 최적해(0)에 도달했다면 가지치기
        if (answer == 0) return;
        
        // 각 열에 대해 구슬 쏘기 시뮬레이션
        for (int j = 0; j < W; j++) {
            int r = -1;
            for (int i = 0; i < H; i++) {
                if (map[i][j] != 0) {
                    r = i;
                    break;
                }
            }
            if (r == -1) continue;  // 해당 열에 벽돌이 없으면 패스
            
            int[][] copiedMap = copyMap(map);
            copiedMap = pop(r, j, copiedMap);
            permWithRep(cnt + 1, copiedMap);
        }
    }
    
    public static void main(String[] args) throws Exception {
        // 입출력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            
            int[][] map = new int[H][W];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            answer = Integer.MAX_VALUE;
            permWithRep(0, map);
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}




///// 예외 케이스 : N번 다 사용하지 않고 0에 도달하는 경우
//package a0410.homework;
//
//import java.io.*;
//import java.util.*;
//
//public class Solution_ur_5656_벽돌깨기_서울_14반_김민준 {
//	
//    static int N, W, H;
//    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
////    static int[][] map;
//    static int answer;
//    
// // deep copy(깊은 복사)
//    static int[][] copyMap(int[][] original) {
//        int[][] copied = new int[H][W];
//        for (int i = 0; i < H; i++) {
//            for (int j = 0; j < W; j++) {
//                copied[i][j] = original[i][j];
//            }
//        }
//        return copied;
//    }
//    
//    static int[][] pop(int r, int c, int[][] map) {
//    	
//    	// bfs로 벽톨 폭파 로직 구현
//    	Queue<int[]> queue = new ArrayDeque<>();
//    	queue.offer(new int[] {r, c, map[r][c]});
//    	map[r][c] = 0;
//    	
//    	while (!queue.isEmpty()) {
//    		int[] xy = queue.poll();
//    		int x = xy[0];
//    		int y = xy[1];
//    		int range = xy[2];
//			
//    		for (int d = 0; d < 4; d++) {
//				for (int k = 1; k < range; k++) {
//					int nx = x + (dx[d] * k);
//					int ny = y + (dy[d] * k);
//					
//					if (nx>=0 && nx<H && ny>=0 && ny<W && map[nx][ny] != 0) {
//						queue.offer(new int[] {nx, ny, map[nx][ny]});
//						map[nx][ny] = 0;
//					}
//				}
//			}
//		}
//    	
//    	// stack 사용해서 벽돌이 밑으로 떨어지도록 구현
//    	for (int j = 0; j < W; j++) {
//			ArrayDeque<Integer> stack = new ArrayDeque<>();
//			for (int i = 0; i < H; i++) {
//				if (map[i][j] != 0) {
//					stack.push(map[i][j]);
//					map[i][j] = 0;
//				}
//			}
//			int s = H;
//			while (!stack.isEmpty()) {
//				map[--s][j] = stack.pop();
//			}
//		}
//    	
//    	return map;
//    }
//    
//    static void permWithRep(int cnt, int[][] map) {
//    	
//    	if (cnt == N) {
//    		int count = 0;
//			for (int i = 0; i < H; i++) {
//				for (int j = 0; j < W; j++) {
//					if (map[i][j] != 0) {
//						++count;
//					}
//				}
//			}
//			answer = Math.min(answer, count);
//			return;
//		}
//    	
//    	for (int j = 0; j < W; j++) {
//    		int r = -1;
//    		for (int i = 0; i < H; i++) {
//				if (map[i][j] != 0) {
//					r = i;
//					break;
//				}
//			}
//    		if (r == -1) continue;
//    		
//    		int[][] copiedMap = copyMap(map); // 현재 맵 상태를 복사
//    	    permWithRep(cnt + 1, pop(r, j, copiedMap));
//    		
//		}
//    	
//    }
//    
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//        StringTokenizer st = null;
//        
//        int T = Integer.parseInt(br.readLine());
//        for (int tc = 1; tc <= T; tc++) {
//            st = new StringTokenizer(br.readLine());
//            N = Integer.parseInt(st.nextToken());
//            W = Integer.parseInt(st.nextToken());
//            H = Integer.parseInt(st.nextToken());
//            
//            int[][] map = new int[H][W];
//            for (int i = 0; i < H; i++) {
//                st = new StringTokenizer(br.readLine());
//                for (int j = 0; j < W; j++) {
//                    map[i][j] = Integer.parseInt(st.nextToken());
//                }
//            }
//            
//            answer = Integer.MAX_VALUE;
//            
//            permWithRep(0, map);
//            
//            sb.append("#").append(tc).append(" ").append(answer).append("\n");
//        }
//        System.out.println(sb);
//    }
//}
//
