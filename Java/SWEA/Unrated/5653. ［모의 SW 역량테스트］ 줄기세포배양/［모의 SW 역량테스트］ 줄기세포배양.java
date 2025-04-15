//package a0415.homework;

import java.io.*;
import java.util.*;

public class Solution {
    // 네 방향(상, 하, 좌, 우)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    // 각 세포 정보를 저장할 클래스
    static class Cell {
        int r, c, life;
        int activeTime;  // 번식(활성화) 시각 = 태어난 시간 + life
        int deathTime;   // 죽는 시각 = 태어난 시간 + 2*life

        public Cell(int r, int c, int life, int birthTime) {
            this.r = r;
            this.c = c;
            this.life = life;
            this.activeTime = birthTime + life + 1;	// 비활성 기간: life시간 후 활성화
            this.deathTime = birthTime + 2 * life;	// 활성 기간: 추가로 life시간 후 죽음
        }
    }

    public static void main(String[] args) throws Exception {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            // 격자 크기: (N + 2*K) x (M + 2*K)
            int totalRows = N + 2 * K;
            int totalCols = M + 2 * K;
            int[][] map = new int[totalRows][totalCols]; // 0이면 비어있고, 0이 아니면 해당 생명력의 세포가 존재

            // 모든 세포 정보를 담을 리스트
            List<Cell> cellList = new ArrayList<>();

            // 초기 세포 배치 (오프셋 = K, 중앙에 배치)
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int life = Integer.parseInt(st.nextToken());
                    if (life > 0) {
                        int r = i + K;
                        int c = j + K;
                        map[r][c] = life;
                        // 초기 세포: 태어난 시간은 0
                        cellList.add(new Cell(r, c, life, 0));
                    }
                }
            }

            // 시뮬레이션: 1시간부터 K시간까지
            // 각 시간 t마다, t에 번식(활성화)되는 세포들을 찾아 번식 처리
            for (int t = 1; t <= K; t++) {
                // (r, c) 위치에 번식 후보 생명력 값을 저장 (여러 세포가 번식하려면 최대 생명력을 선택)
                // key = r * totalCols + c, value = life
                HashMap<Integer, Integer> candidate = new HashMap<>();

                // 현재 시간 t에 활성화되는 세포들에 대해
                for (Cell cell : cellList) {
                    if (cell.activeTime == t) {  // 이 세포가 t시에 활성화되어 번식한다.
                        for (int d = 0; d < 4; d++) {
                            int nr = cell.r + dx[d];
                            int nc = cell.c + dy[d];
                            // 격자 범위 체크
                            if (nr < 0 || nr >= totalRows || nc < 0 || nc >= totalCols) continue;
                            // 이미 세포가 자리하고 있다면 번식 불가능 (죽은 세포도 자리를 차지)
                            if (map[nr][nc] != 0) continue;
                            int key = nr * totalCols + nc;
                            // 같은 시간에 다른 세포가 번식 시도한 경우 생명력 큰 세포로 업데이트
                            if (!candidate.containsKey(key) || candidate.get(key) < cell.life) {
                                candidate.put(key, cell.life);
                            }
                        }
                    }
                }
                // 후보 정보를 바탕으로 새 세포들을 생성한다.
                for (Map.Entry<Integer, Integer> entry : candidate.entrySet()) {
                    int key = entry.getKey();
                    int candidateLife = entry.getValue();
                    int nr = key / totalCols;
                    int nc = key % totalCols;
                    // 아직 다른 세포가 들어오지 않았다면 처리 (격자 검사)
                    if (map[nr][nc] == 0) {
                        map[nr][nc] = candidateLife;
                        // 새 세포의 태어난 시간은 현재 시간 t
                        cellList.add(new Cell(nr, nc, candidateLife, t));
                    }
                }
                
//                // 디버깅용
//                for (int[] m : map) {
//                	for (int u : m) {
//						System.out.print(u);
//					}
//                	System.out.println();
//                }
            }

            // 시뮬레이션 종료 후, 살아있는 세포(비활성 상태 및 활성 상태: 죽는 시간이 K보다 큰 세포) 수 계산
            int answer = 0;
            for (Cell cell : cellList) {
                if (cell.deathTime > K) {
                    answer++;
                }
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}



/// 폐기!
//package a0415.homework;
//
//import java.io.*;
//import java.util.*;
//
//public class Solution_ur_5653_줄기세포배양_서울_14반_김민준 {
//    
////    static int N, M, K;
//    static int[] dx = {-1, 1, 0, 0};
//    static int[] dy = {0, 0, -1, 1};
//    
//    public static void main(String[] args) throws Exception {
//    	
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//        int T = Integer.parseInt(br.readLine());
//        
//        for (int tc = 1; tc <= T; tc++) {
//            StringTokenizer st = new StringTokenizer(br.readLine());
//            int N = Integer.parseInt(st.nextToken());
//            int M = Integer.parseInt(st.nextToken());
//            int K = Integer.parseInt(st.nextToken());
//            
////            int[][] map = new int[N][M];
//            int[][] map = new int[N*K][M*K];	// 줄기 세포 크기가 늘어나기 때문에 배열 크기를 K를 곱해줌..?
////            int[][] timestamp = new int[N*K][M*K];	// 발생 시간을 알아야함, map에는 몇시간이 지나야할지 나오니깐
////            Queue<int[]> cells = new ArrayDeque<>();	// 줄기 세포 큐
//            Queue<int[]> cells = new PriorityQueue<>(new Comparator<int[]>() {
//            	@Override
//            	public int compare(int[] o1, int[] o2) {
//            		return o1[2] - o2[2];
//            	}
//			});
//            
//            for (int i = (N*K)/2 - N/2; i < (N*K)/2 + N/2; i++) {
//                st = new StringTokenizer(br.readLine());
//                for (int j = (M*K)/2 - M/2; j < (M*K)/2 + M/2; j++) {
//                    map[i][j] = Integer.parseInt(st.nextToken());
//                    if (map[i][j] != 0) {
////                    	timestamp[i][j] = 0;	// 세포 최초 생성 시간
//                    	cells.offer(new int[] {i, j, map[i][j] + 1});
//                    }
//                }
//            }
//            
//            for (int t = 1; t <= K; t++) {
//				while (!cells.isEmpty() && cells.peek()[2] == t) {
//					int[] cell = cells.poll();
//					for (int d = 0; d < 4; d++) {
//						int nx = cell[0] + dx[d];
//						int ny = cell[1] + dy[d];
//						if (nx>=0 && nx<N*K && ny>=0 && ny<M*K && map[nx][ny] == 0) {
//							map[nx][ny] = map[cell[0]][cell[1]];
//							cells.offer(new int[] {nx, ny, map[nx][ny] + 1});
//						}
//					}					
//				}
//			}
//            
//            int answer = 0;
//            for (int i = 0; i < N*K; i++) {
//				for (int j = 0; j < M*K; j++) {
//					if (map[i][j] != 0) ++answer;
//				}
//			}
//            
//            
//            sb.append("#").append(tc).append(" ").append(answer).append("\n");
//        }
//        System.out.println(sb);
//    }
//}
//
