//package a0305.homework;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M, walls, answer;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    static void comb(int index, int count, int[][] map, ArrayList<int[]> rooms, ArrayList<int[]> viruses) {
        if (count == 3) {
            infection(map, viruses);
            return;
        }

        for (int i = index; i < rooms.size(); i++) {
            int[] xy = rooms.get(i);
            map[xy[0]][xy[1]] = 1; // 벽 세우기
            ++walls;
            comb(i + 1, count + 1, map, rooms, viruses);
            map[xy[0]][xy[1]] = 0; // 원상 복구
            --walls;
        }
    }

    private static void infection(int[][] map, ArrayList<int[]> viruses) {
        int[][] tempMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            tempMap[i] = map[i].clone();
        }

        int infected = 0;
        
        Queue<int[]> q = new ArrayDeque<>();
        for (int[] virus : viruses) {
            q.offer(virus);
            infected++;
        }

        while (!q.isEmpty()) {
            int[] xy = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = xy[0] + dx[d], ny = xy[1] + dy[d];
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && tempMap[nx][ny] == 0) {
                    tempMap[nx][ny] = 2; // 감염
                    infected++;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
        answer = Math.max(answer, N*M - infected - walls);

//        int safeArea = 0;
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                if (tempMap[i][j] == 0) {
//                    safeArea++;
//                }
//            }
//        }
//        answer = Math.max(answer, safeArea);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        ArrayList<int[]> rooms = new ArrayList<>();
        ArrayList<int[]> viruses = new ArrayList<>();
        walls = 0;
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    rooms.add(new int[]{i, j});
                } else if (map[i][j] == 1) {
                	++walls;
                } else if (map[i][j] == 2) {
                    viruses.add(new int[]{i, j});
                }
            }
        }

        answer = 0;
        comb(0, 0, map, rooms, viruses);
        System.out.println(answer);
    }
}


/// 무한 루프 (잘못된 조합 코드!)
//package a0305.homework;
//
//import java.io.*;
//import java.util.*;
//
//public class Main_bj_14502_연구소_서울_14반_김민준 {
//	
//	static int N, M, walls, answer;
//	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
//	
//	static void comb(int index, int count, int[][] map, ArrayList<int[]> rooms, ArrayList<int[]> viruses) {
//		if (count == 3) {
//			infection(map, viruses);
//		}
//		
//		for (int i = index; i < rooms.size(); i++) {
//			int[]xy = rooms.get(i);
//			map[xy[0]][xy[1]] = 1;
//			comb(index + 1, count+1, map, rooms, viruses);
//			map[xy[0]][xy[1]] = 0;
//			
//			comb(index + 1, count, map, rooms, viruses);
//		}
//		
//	}
//	
//	private static void infection(int[][] map, ArrayList<int[]> viruses) {
//		
//		int count = 0;
//		for (int[] rc : viruses) {
//			Queue<int[]> q = new ArrayDeque<>();
//			q.offer(new int[] {rc[0], rc[1]});
//			++count;
//			
//			while (!q.isEmpty()) {
//				int[]xy = q.poll();
//				
//				for (int d = 0; d < 4; d++) {
//					int nx = xy[0] + dx[d], ny = xy[1] +dy[d];
//					if (nx>=0 && nx<N && ny>=0 && ny<M && map[nx][ny] == 0) {
//						map[nx][ny] = 3;	// 감염 완료
//						count++;
//						q.offer(new int[] {nx, ny});
//					}
//				}
//			}
//		}
//		
//		answer = Math.max(answer, N*M - count - walls);
//	}
//
//	public static void main(String[] args) throws Exception {
//
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		N = Integer.parseInt(st.nextToken());
//		M = Integer.parseInt(st.nextToken());
//		
//		int[][] map = new int[N][M];
//		
//		ArrayList<int[]> rooms = new ArrayList<>();
//		ArrayList<int[]> viruses = new ArrayList<>();		
//		walls = 0;
//		
//		for (int i = 0; i < N; i++) {
//			st = new StringTokenizer(br.readLine());
//			for (int j = 0; j < M; j++) {
//				map[i][j] = Integer.parseInt(st.nextToken());
//				if (map[i][j] == 0) {
//					rooms.add(new int[] {i, j});
//				} else if (map[i][j] == 1) {
//					++walls;
//				} else if (map[i][j] == 2) {
//					viruses.add(new int[] {i, j});
//				}
//			}
//		}
//		
//		answer = 0;
//		
//		comb(0, 0, map, rooms, viruses);
//		
//		System.out.println(answer);
//	}
//}
