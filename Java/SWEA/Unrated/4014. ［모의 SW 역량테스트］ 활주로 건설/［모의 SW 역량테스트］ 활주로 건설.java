//package a0409.homework;

import java.io.*;
import java.util.*;

public class Solution {
    static int N, X;
    static int[][] map;
    
    // 한 행 또는 열이 활주로 설치가 가능한지 판단하는 함수
    static boolean canBuildRunway(int[] line) {
        boolean[] used = new boolean[N];  // 해당 위치에 이미 경사로 설치 여부
        
        for (int i = 0; i < N - 1; i++) {
            // 높이가 같은 경우는 넘어감
            if (line[i] == line[i+1]) continue;
            
            // 높이 차이가 1보다 크면 설치 불가
            if (Math.abs(line[i] - line[i+1]) > 1) return false;
            
            // 오르막: i+1이 1 높다 -> i부터 뒤로 X칸이 모두 line[i]여야 함
            if (line[i+1] - line[i] == 1) {
                for (int j = i; j > i - X; j--) {
                    if (j < 0 || line[j] != line[i] || used[j]) return false;
                    used[j] = true;  // 경사로를 설치함
                }
            }
            // 내리막: i+1이 1 낮다 -> i+1부터 앞으로 X칸이 모두 line[i+1]이어야 함
            else if (line[i] - line[i+1] == 1) {
                for (int j = i + 1; j <= i + X; j++) {
                    if (j >= N || line[j] != line[i+1] || used[j]) return false;
                    used[j] = true;  // 경사로를 설치함
                }
            }
        }
        return true;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            int answer = 0;
            
            // 각 행에 대해 활주로 설치 가능 여부 검사
            for (int i = 0; i < N; i++) {
                if (canBuildRunway(map[i])) {
                    answer++;
                    // 설치 가능 행 출력 (디버깅 용)
                    // System.out.println("row: " + i);
                }
            }
            
            // 각 열에 대해서도 검사
            for (int j = 0; j < N; j++) {
                int[] col = new int[N];
                for (int i = 0; i < N; i++) {
                    col[i] = map[i][j];
                }
                if (canBuildRunway(col)) {
                    answer++;
                    // 설치 가능 열 출력 (디버깅 용)
                    // System.out.println("col: " + j);
                }
            }
            
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}


/// 실패
//package a0409.homework;
//
//import java.io.*;
//import java.util.*;
//
//public class Solution_ur_4014_활주로건설_서울_14반_김민준 {
//	
//	static int N, X;
//	static int[][] map;
//	
//	// 가로 확인 (dir -1:오르막길(이전 열 확인) 1:내리막길(이후 열 확인)
//	static boolean horizontal(int r, int c, int dir) {
//		boolean can = true;
//		int start = map[r][c];
//		
//		if (dir == -1) {
//			for (int j = c-1; j >= c-X; j--) {
//				if (j>=0 && j<N && map[r][j] == start - 1) {
//					continue;
//				} else {
//					can = false;
//					break;
//				}
//			}
//		} else { // dir == -1
//			for (int j = c+1; j <= c+X; j++) {
//				if (j>=0 && j<N && map[r][j] == start + 1) {
//					continue;
//				} else {
//					can = false;
//					break;
//				}
//			}			
//		}
//		
//		return can;
//	}
//	
//	// 세로 확인 (dir -1:오르막길(이전 행 확인) 1:내리막길(이후 행 확인)
//	static boolean vertical(int r, int c, int dir) {
//		boolean can = true;
//		int start = map[r][c];
//		
//		if (dir == -1) {
//			for (int i = r-1; i >= r-X; i--) {
//				if (i>=0 && i<N && map[i][c] == start - 1) {
//					continue;
//				} else {
//					can = false;
//					break;
//				}
//			}
//		} else { // dir == -1
//			for (int i = r+1; i <= r+X; i++) {
//				if (i>=0 && i<N && map[i][c] == start + 1) {
//					continue;
//				} else {
//					can = false;
//					break;
//				}
//			}			
//		}
//		
//		return can;		
//	}
//
//	public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//        StringTokenizer st = null;
//        int T = Integer.parseInt(br.readLine());
//        
//        for (int tc = 1; tc <= T; tc++) {
//        	
//        	st = new StringTokenizer(br.readLine());
//        	N = Integer.parseInt(st.nextToken());
//        	X = Integer.parseInt(st.nextToken());
//        	
//        	map = new int[N][N];
//        	
//        	for (int i = 0; i < N; i++) {
//        		st = new StringTokenizer(br.readLine());
//				for (int j = 0; j < N; j++) {
//					map[i][j] = Integer.parseInt(st.nextToken());
//				}
//			}
//        	
//            
//        	int answer = 0;
//        	
//        	for (int i = 0; i < N; i++) {
//        		int prev = map[i][0];
//        		boolean can = true;
//        		for (int j = 1; j < N; j++) {
//        			if (map[i][j] == prev) {
//        				continue;
//        			} else if (map[i][j] == prev + 1) { // 오르막길
//						if (horizontal(i, j, -1)) {
//							prev = map[i][j];
//							continue;
//						} else {
//							can = false;
//							break;
//						}
//					} else if (map[i][j] == prev - 1) { // 내리막길
//						if (horizontal(i, j, 1)) {
//							prev = map[i][j];
//							continue;
//						} else {
//							can = false;
//							break;
//						}						
//					} else {
//						can = false;
//						break;
//					}
//				}
//        		if (can) {
//        			++answer;
//        			System.out.println("r: " + i);
//        		}
//			}
//        	
//        	for (int j = 0; j < N; j++) {
//        		int prev = map[0][j];
//        		boolean can = true;
//        		for (int i = 1; i < N; i++) {
//        			if (map[i][j] == prev) {
//        				continue;
//        			} else if (map[i][j] == prev + 1) { // 오르막길
//        				if (vertical(i, j, -1)) {
//        					prev = map[i][j];
//        					continue;
//        				} else {
//        					can = false;
//        					break;
//        				}
//        			} else if (map[i][j] == prev - 1) { // 내리막길
//        				if (vertical(i, j, 1)) {
//        					prev = map[i][j];
//        					continue;
//        				} else {
//        					can = false;
//        					break;
//        				}						
//        			} else {
//        				can = false;
//        				break;
//        			}
//        		}
//        		if (can) {
//        			++answer;
//        			System.out.println("c: " + j);
//        		}
//        	}
//        	System.out.println();
//            sb.append("#").append(tc).append(" ").append(answer).append("\n");
//        }
//        
//        System.out.println(sb.toString());
//    }
//}
//
