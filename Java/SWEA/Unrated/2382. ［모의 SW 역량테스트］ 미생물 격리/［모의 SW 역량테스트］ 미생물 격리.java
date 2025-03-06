//package a0306.homework;

import java.io.*;
import java.util.*;

public class Solution {

    // 각 군집을 표현하는 클래스
    static class Group {
        int r, c, count, dir;
        public Group(int r, int c, int count, int dir) {
            this.r = r;
            this.c = c;
            this.count = count;
            this.dir = dir;
        }
    }
    
    // 0: 상, 1: 하, 2: 좌, 3: 우 (문제 입력은 상(1), 하(2), 좌(3), 우(4))
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    // 경계에서 방향 반전을 위한 메서드
    static int reverseDir(int dir) {
        if(dir == 0) return 1;
        else if(dir == 1) return 0;
        else if(dir == 2) return 3;
        else return 2;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());   // 셀의 개수
            int M = Integer.parseInt(st.nextToken());   // 격리 시간
            int K = Integer.parseInt(st.nextToken());   // 미생물 군집의 개수
            
            // 초기 군집 정보 저장 (군집들은 경계가 아닌 내부에만 배치됨)
            List<Group> groups = new ArrayList<>();
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;  // 0-index: 0:상, 1:하, 2:좌, 3:우
                groups.add(new Group(r, c, count, dir));
            }
            
            // M시간 동안 시뮬레이션 진행
            for (int t = 0; t < M; t++) {
                // 새로운 위치에 도착한 군집들을 저장할 2차원 리스트 배열 생성
                List<Group>[][] newGrid = new ArrayList[N][N];
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        newGrid[i][j] = new ArrayList<>();
                    }
                }
                
                // 각 군집을 이동 처리
                for (Group g : groups) {
                    int nr = g.r + dr[g.dir];
                    int nc = g.c + dc[g.dir];
                    int nCount = g.count;
                    int ndir = g.dir;
                    
                    // 경계(약품 있는 셀)에 도착하면 미생물 수가 절반으로 줄고, 이동 방향이 반대로 바뀜
                    if(nr == 0 || nr == N - 1 || nc == 0 || nc == N - 1) {
                        nCount = nCount / 2;
                        ndir = reverseDir(ndir);
                    }
                    // 미생물 수가 0이면 군집은 소멸
                    if(nCount > 0) {
                        newGrid[nr][nc].add(new Group(nr, nc, nCount, ndir));
                    }
                }
                
                // 같은 셀에 도착한 군집들을 합치기
                groups.clear();
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if(newGrid[i][j].size() == 0) continue;
                        if(newGrid[i][j].size() == 1) {
                            groups.add(newGrid[i][j].get(0));
                        } else {
                            int total = 0;
                            int maxCount = -1;
                            int chosenDir = 0;
                            for (Group g : newGrid[i][j]) {
                                total += g.count;
                                if(g.count > maxCount) {
                                    maxCount = g.count;
                                    chosenDir = g.dir;
                                }
                            }
                            groups.add(new Group(i, j, total, chosenDir));
                        }
                    }
                }
            }
            
            // 최종 남은 미생물 수 합 계산
            int answer = 0;
            for (Group g : groups) {
                answer += g.count;
            }
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        
        System.out.println(sb.toString());
    }
}

///// 도착지를 기준으로 저장하는 방법 실패!
//package a0306.homework;
//
//import java.io.*;
//import java.util.*;
//
//public class Solution_ur_2382_미생물격리_서울_14반_김민준 {
//
//    static class Pair {
//        int x;
//        int y;
//        public Pair(int x, int y) {
//            this.x = x;
//            this.y = y;
//        }
//        
//        @Override
//        public boolean equals(Object obj) {
//            if (this == obj) return true;
//            if (!(obj instanceof Pair)) return false;
//            Pair that = (Pair) obj;
//            return x == that.x && y == that.y;
//        }
//        
//        @Override
//        public int hashCode() {
//            return Objects.hash(x, y);
//        }
//    }
//    
//    static void move(int x, int y) {
//        int maxS = 0;
//        int maxD = 0;
//        int sumS = 0;
//        
//        // 가장자리 셀인 경우 약품 효과 적용
//        if (x == 0 || x == N - 1 || y == 0 || y == N - 1) {
//            for (int d = 0; d < 4; d++) {
//                int nx = x - dx[d];
//                int ny = y - dy[d];
//                if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] != 0) {
//                    // 방향은 하단 2비트에 저장되어 있으므로 & 3 사용 (3 == 0b11)
//                    int currD = map[nx][ny] & 3;
//                    int currS = map[nx][ny] >> 2;
//                    if (currD == (d + 2) % 4) {
//                        if (maxS < currS) {
//                            maxS = currS;
//                            maxD = currD;
//                        }
//                        sumS += currS;
//                        map[nx][ny] = 0;
//                    }
//                }
//            }
//            sumS /= 2; // 약품 효과에 의해 절반으로 감소
//            maxD = (maxD % 2 == 0) ? maxD + 1 : maxD - 1; // 방향 반전
//        } else {
//            for (int d = 0; d < 4; d++) {
//                int nx = x - dx[d];
//                int ny = y +- dy[d];
//                if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] != 0) {
//                    int currD = map[nx][ny] & 3;
//                    int currS = map[nx][ny] >> 2;
//                    if (currD == (d + 2) % 4) {
//                        if (maxS < currS) {
//                            maxS = currS;
//                            maxD = currD;
//                        }
//                        sumS += currS;
//                        map[nx][ny] = 0;
//                    }
//                }
//            }
//        }
//        
//        // 미생물 수와 방향을 다시 저장 (미생물 수는 왼쪽으로 2비트, 방향은 하단 2비트)
//        map[x][y] = (sumS << 2) + maxD;
//        int ndx = x + dx[maxD];
//        int ndy = y + dy[maxD];
//        if (ndx >= 0 && ndx < N && ndy >= 0 && ndy < N) {
//        	dests.add(new Pair(ndx, ndy));
//        } else {
//        	maxD = (maxD % 2 == 0) ? maxD + 1 : maxD - 1;
//        	ndx = x + dx[maxD];
//        	ndy = y + dy[maxD];        	
//        	dests.add(new Pair(ndx, ndy));
//        }
//    }
//    
//    // 0: 상, 1: 하, 2: 좌, 3: 우
//    static int[] dx = {-1, 1, 0, 0};
//    static int[] dy = {0, 0, -1, 1};
//    
//    static int N, M, K;
//    static int[][] map;
//    static Set<Pair> dests;
//    
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = null;
//        StringBuilder sb = new StringBuilder();
//        
//        int T = Integer.parseInt(br.readLine());
//        
//        for (int tc = 1; tc <= T; tc++) {
//            st = new StringTokenizer(br.readLine());
//            N = Integer.parseInt(st.nextToken());  // 셀의 개수
//            M = Integer.parseInt(st.nextToken());  // 격리 시간
//            K = Integer.parseInt(st.nextToken());  // 미생물 군집의 개수
//            
//            map = new int[N][N];
//            dests = new HashSet<>();
//            
//            for (int i = 0; i < K; i++) {
//                st = new StringTokenizer(br.readLine());
//                int r = Integer.parseInt(st.nextToken());
//                int c = Integer.parseInt(st.nextToken());
//                int size = Integer.parseInt(st.nextToken());
//                int direction = Integer.parseInt(st.nextToken()) - 1; // 0: 상, 1: 하, 2: 좌, 3: 우
//                // 미생물 수와 방향을 하나의 정수로 저장 (미생물 수는 왼쪽으로 2비트 쉬프트)
//                map[r][c] = (size << 2) + direction;
//                int nx = r + dx[direction];
//                int ny = c + dy[direction];
//                dests.add(new Pair(nx, ny));
//            }
//            
//            // M 시간 동안 이동 수행
//            for (int t = 0; t < M; t++) {
//                Set<Pair> currentDests = new HashSet<>(dests);
//                dests.clear();
//                for (Pair dest : currentDests) {
//                    move(dest.x, dest.y);
//                }
//            }
//            
//            int answer = 0;
//            // 남아있는 모든 미생물 수의 합 계산
//            for (int i = 0; i < N; i++) {
//                for (int j = 1; j < N - 1; j++) {
//                    answer += (map[i][j] >> 2);
//                }
//            }
//            
//            sb.append("#").append(tc).append(" ").append(answer).append("\n");
//        }
//        
//        System.out.println(sb.toString());
//    }
//}
