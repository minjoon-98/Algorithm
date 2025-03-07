package B형특강.swea_14596_Pro_섬지키기;

import java.util.*;

class UserSolution {
    int N;              // 섬의 크기 (N x N)
    int[][] map;        // 섬의 고도 정보

    // 초기화: mMap의 정보를 내부 map에 복사
    public void init(int N, int mMap[][]) {
        this.N = N;
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = mMap[i][j];
            }
        }
    }

    // 구조물 설치 후보의 개수를 반환
    public int numberOfCandidate(int M, int mStructure[]) {
        int count = 0;
        // M==1인 경우, 한 칸이 후보이므로 전체 N*N개가 후보임.
        if (M == 1) {
            return N * N;
        }
        // 가로 방향 후보: 한 행에서 연속 M칸 (좌→우)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= N - M; j++) {
                if (checkCandidateHorizontal(i, j, M, mStructure))
                    count++;
            }
        }
        // 세로 방향 후보: 한 열에서 연속 M칸 (상→하)
        for (int j = 0; j < N; j++) {
            for (int i = 0; i <= N - M; i++) {
                if (checkCandidateVertical(i, j, M, mStructure))
                    count++;
            }
        }
        return count;
    }

    // 가로 후보 검증: (i, j)부터 연속 M칸에 대해 원래 순서 또는 역순 중 하나라도 조건 충족하면 true
    private boolean checkCandidateHorizontal(int i, int j, int M, int[] mStructure) {
        if (isValidHorizontal(i, j, M, mStructure, false)) return true;
        if (isValidHorizontal(i, j, M, mStructure, true)) return true;
        return false;
    }

    // 가로 방향 후보의 유효성 검사 (reversed가 false면 원래 순서, true면 역순)
    private boolean isValidHorizontal(int i, int j, int M, int[] mStructure, boolean reversed) {
        int s0 = reversed ? mStructure[M - 1] : mStructure[0];
        int target = map[i][j] + s0; // 첫 칸의 최종 고도 기준
        for (int k = 1; k < M; k++) {
            int structureHeight = reversed ? mStructure[M - 1 - k] : mStructure[k];
            if (map[i][j + k] + structureHeight != target)
                return false;
        }
        return true;
    }

    // 세로 후보 검증: (i, j)부터 아래로 연속 M칸에 대해 검사
    private boolean checkCandidateVertical(int i, int j, int M, int[] mStructure) {
        if (isValidVertical(i, j, M, mStructure, false)) return true;
        if (isValidVertical(i, j, M, mStructure, true)) return true;
        return false;
    }

    // 세로 방향 후보의 유효성 검사
    private boolean isValidVertical(int i, int j, int M, int[] mStructure, boolean reversed) {
        int s0 = reversed ? mStructure[M - 1] : mStructure[0];
        int target = map[i][j] + s0;
        for (int k = 1; k < M; k++) {
            int structureHeight = reversed ? mStructure[M - 1 - k] : mStructure[k];
            if (map[i + k][j] + structureHeight != target)
                return false;
        }
        return true;
    }

    // 해수면 상승 후 최대 건조 영역(침수되지 않는 셀의 수)을 반환
    // 후보가 하나도 없으면 -1을 반환
    public int maxArea(int M, int mStructure[], int mSeaLevel) {
        int best = -1;
        boolean foundCandidate = false;
        // M==1인 경우, 가로 방향(또는 세로 방향)은 동일하므로 한 번만 순회
        if (M == 1) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 한 칸은 언제나 유효 (조건이 자동 만족)
                    foundCandidate = true;
                    int area = simulateFloodForSingleCell(i, j, mStructure[0], mSeaLevel);
                    best = Math.max(best, area);
                }
            }
        } else {
            // 가로 방향 후보에 대해 시뮬레이션
            for (int i = 0; i < N; i++) {
                for (int j = 0; j <= N - M; j++) {
                    if (isValidHorizontal(i, j, M, mStructure, false)) {
                        foundCandidate = true;
                        int area = simulateFloodHorizontal(i, j, M, mStructure, false, mSeaLevel);
                        best = Math.max(best, area);
                    } else if (isValidHorizontal(i, j, M, mStructure, true)) {
                        foundCandidate = true;
                        int area = simulateFloodHorizontal(i, j, M, mStructure, true, mSeaLevel);
                        best = Math.max(best, area);
                    }
                }
            }
            // 세로 방향 후보에 대해 시뮬레이션
            for (int j = 0; j < N; j++) {
                for (int i = 0; i <= N - M; i++) {
                    if (isValidVertical(i, j, M, mStructure, false)) {
                        foundCandidate = true;
                        int area = simulateFloodVertical(i, j, M, mStructure, false, mSeaLevel);
                        best = Math.max(best, area);
                    } else if (isValidVertical(i, j, M, mStructure, true)) {
                        foundCandidate = true;
                        int area = simulateFloodVertical(i, j, M, mStructure, true, mSeaLevel);
                        best = Math.max(best, area);
                    }
                }
            }
        }
        return foundCandidate ? best : -1;
    }

    // M==1인 경우, (i, j) 한 칸에 구조물(높이 mStructure[0])을 설치한 후 침수 시뮬레이션
    private int simulateFloodForSingleCell(int i, int j, int structureHeight, int mSeaLevel) {
        int[][] temp = copyMap();
        temp[i][j] += structureHeight;
        return floodFill(temp, mSeaLevel);
    }

    // 가로 방향 후보에 대해, (i, j)부터 연속 M칸에 구조물(원래 또는 역순)을 설치한 후 침수 시뮬레이션
    private int simulateFloodHorizontal(int i, int j, int M, int[] mStructure, boolean reversed, int mSeaLevel) {
        int[][] temp = copyMap();
        for (int k = 0; k < M; k++) {
            int add = reversed ? mStructure[M - 1 - k] : mStructure[k];
            temp[i][j + k] += add;
        }
        return floodFill(temp, mSeaLevel);
    }

    // 세로 방향 후보에 대해, (i, j)부터 아래로 연속 M칸에 구조물 설치 후 침수 시뮬레이션
    private int simulateFloodVertical(int i, int j, int M, int[] mStructure, boolean reversed, int mSeaLevel) {
        int[][] temp = copyMap();
        for (int k = 0; k < M; k++) {
            int add = reversed ? mStructure[M - 1 - k] : mStructure[k];
            temp[i + k][j] += add;
        }
        return floodFill(temp, mSeaLevel);
    }

    // 현재 map을 복사하여 새로운 2차원 배열로 반환
    private int[][] copyMap() {
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, copy[i], 0, N);
        }
        return copy;
    }

    // 침수 시뮬레이션: 해수면이 mSeaLevel만큼 상승하면, 고도가 (mSeaLevel-1) 이하인 셀은 물에 잠김
    // 섬의 외곽(경계)부터 BFS로 침수 영역을 찾고, 건조한 셀의 수(전체 셀 수 - 침수 셀 수)를 반환
    private int floodFill(int[][] temp, int mSeaLevel) {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> queue = new LinkedList<>();

        // 섬의 경계 셀에서 시작: 해수면 침수가 가능한 셀 (고도 <= mSeaLevel-1)
        for (int i = 0; i < N; i++) {
            if (!visited[i][0] && temp[i][0] <= mSeaLevel - 1) {
                visited[i][0] = true;
                queue.offer(new int[]{i, 0});
            }
            if (!visited[i][N - 1] && temp[i][N - 1] <= mSeaLevel - 1) {
                visited[i][N - 1] = true;
                queue.offer(new int[]{i, N - 1});
            }
        }
        for (int j = 0; j < N; j++) {
            if (!visited[0][j] && temp[0][j] <= mSeaLevel - 1) {
                visited[0][j] = true;
                queue.offer(new int[]{0, j});
            }
            if (!visited[N - 1][j] && temp[N - 1][j] <= mSeaLevel - 1) {
                visited[N - 1][j] = true;
                queue.offer(new int[]{N - 1, j});
            }
        }

        int floodedCount = 0;
        int[] di = { -1, 1, 0, 0 };
        int[] dj = { 0, 0, -1, 1 };
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int ci = cur[0], cj = cur[1];
            floodedCount++;
            for (int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if (ni < 0 || ni >= N || nj < 0 || nj >= N)
                    continue;
                if (!visited[ni][nj] && temp[ni][nj] <= mSeaLevel - 1) {
                    visited[ni][nj] = true;
                    queue.offer(new int[]{ni, nj});
                }
            }
        }
        int total = N * N;
        return total - floodedCount;
    }
}



///// 정답 코드
//
//import java.util.*;
//
//class UserSolution {
//    // 섬의 최대 크기와 후보 해시값의 최대 범위 (문제에서 구조물 길이 최대 5이므로 차이 조합이 제한됨)
//    public final int MAX_N = 20;
//    public final int MAX_HASH = 9999;
//
//    // 현재 섬의 크기
//    public int n;
//    // 원본 섬 지도와 수정된 섬 지도를 저장 (패딩을 위해 배열 크기를 n+2 x n+2로 사용)
//    public int[][] initMap = new int[MAX_N + 2][MAX_N + 2];
//    public int[][] modifiedMap = new int[MAX_N + 2][MAX_N + 2];
//
//    // 구조물 설치 후보를 표현하는 클래스
//    public class Candidate {
//        int r;              // 후보의 시작 행 (패딩을 고려하여 1부터 n)
//        int c;              // 후보의 시작 열 (패딩을 고려하여 1부터 n)
//        boolean isHorizontal; // true면 가로 방향, false면 세로 방향
//        boolean isReverse;    // true면 구조물 배열의 역순(회전 후 반전)으로 적용
//
//        public Candidate(int r, int c, boolean isHorizontal, boolean isReverse) {
//            this.r = r;
//            this.c = c;
//            this.isHorizontal = isHorizontal;
//            this.isReverse = isReverse;
//        }
//    }
//
//    // 각 해시값(구조물 높이 차이 패턴)에 해당하는 후보 리스트를 저장합니다.
//    // 해시값은 0~MAX_HASH 범위로 사용
//    public List<Candidate>[] candidate = new List[MAX_HASH + 1];
//
//    // init 함수: 섬의 초기 정보를 받아 내부 배열에 저장하고, 후보 위치를 미리 계산하여 해시 테이블에 저장
//    public void init(int N, int[][] mMap) {
//        n = N;
//        // 패딩을 위해 인덱스를 1부터 n으로 사용, 0과 n+1 행/열은 경계(물로 처리됨)
//        for (int i = 0; i < n; i++)
//            for (int j = 0; j < n; j++)
//                // initMap과 modifiedMap에 원본 mMap 정보를 복사 (패딩을 위해 +1 offset)
//                modifiedMap[i + 1][j + 1] = initMap[i + 1][j + 1] = mMap[i][j];
//
//        // 해시값 범위 내의 각 인덱스를 초기화 (각 후보 리스트를 생성)
//        for (int i = 0; i <= MAX_HASH; i++)
//            candidate[i] = new ArrayList<>();
//
//        // 구조물의 길이는 2부터 5까지 가능하므로 각각에 대해 후보 위치를 미리 계산
//        for (int length = 2; length <= 5; length++) {
//            // 먼저 가로 방향 후보
//            for (int i = 1; i <= n; i++) {
//                // 시작 열: j부터 연속 length칸이 섬 내부에 존재해야 하므로 j + length - 1 <= n
//                for (int j = 1; j + length - 1 <= n; j++) {
//                    int hash = 0;
//                    // 해당 가로 구간의 연속 셀 간 높이 차이를 해시값으로 계산
//                    // k: 0부터 length-2까지 (즉, 인접 두 칸의 차이를 모두 반영)
//                    for (int k = 0; k + 1 < length; k++)
//                        // 차이값에 5를 더하는 이유는 음수 값을 양수로 바꾸기 위함 (범위 보정)
//                        hash = hash * 10 + (initMap[i][j + k + 1] - initMap[i][j + k] + 5);
//                    // 가로 방향, 원래 순서일 때의 후보 추가
//                    candidate[hash].add(new Candidate(i, j, true, false));
//
//                    // 역순(구조물을 뒤집어 놓은 경우)의 해시값 계산
//                    int reverseHash = 0;
//                    for (int k = length - 1; k - 1 >= 0; k--)
//                        reverseHash = reverseHash * 10 + (initMap[i][j + k - 1] - initMap[i][j + k] + 5);
//                    // 역순이 원래와 다르면 중복 추가를 피하기 위해 체크
//                    if (reverseHash != hash)
//                        candidate[reverseHash].add(new Candidate(i, j, true, true));
//                }
//            }
//            // 이제 세로 방향 후보
//            for (int i = 1; i + length - 1 <= n; i++) {
//                for (int j = 1; j <= n; j++) {
//                    int hash = 0;
//                    // 세로 방향 연속 셀 간의 높이 차이를 해시값으로 계산
//                    for (int k = 0; k + 1 < length; k++)
//                        hash = hash * 10 + (initMap[i + k + 1][j] - initMap[i + k][j] + 5);
//                    candidate[hash].add(new Candidate(i, j, false, false));
//
//                    // 세로 방향 역순 해시 계산
//                    int reverseHash = 0;
//                    for (int k = length - 1; k - 1 >= 0; k--)
//                        reverseHash = reverseHash * 10 + (initMap[i + k - 1][j] - initMap[i + k][j] + 5);
//                    if (reverseHash != hash)
//                        candidate[reverseHash].add(new Candidate(i, j, false, true));
//                }
//            }
//        }
//    }
//
//    // numberOfCandidate: 구조물 mStructure를 설치할 수 있는 후보 수를 반환
//    // mStructure 배열의 길이 M가 1이면, 모든 칸이 후보가 되므로 n*n을 반환
//    public int numberOfCandidate(int M, int[] mStructure) {
//        if (M == 1)
//            return n * n;
//        // 구조물의 연속 차이를 해시값으로 계산
//        int hash = 0;
//        for (int i = 0; i + 1 < M; i++)
//            hash = hash * 10 + (mStructure[i] - mStructure[i + 1] + 5);
//        // 미리 계산된 후보 리스트의 크기가 정답
//        return candidate[hash].size();
//    }
//
//    // BFS에서 사용할 방문 체크 배열와 4방향 이동 벡터 (동, 남, 서, 북)
//    public boolean[][] check = new boolean[MAX_N + 2][MAX_N + 2];
//    public int[] dx = {1, 0, -1, 0};
//    public int[] dy = {0, 1, 0, -1};
//
//    // unsubmergedArea: 주어진 지도(mMap)에서 해수면(mSeaLevel) 기준으로 침수되지 않은 면적(건조 영역)을 계산
//    public int unsubmergedArea(int[][] mMap, int mSeaLevel) {
//        Queue<int[]> q = new LinkedList<>();
//        // 패딩된 경계 전체(0행, n+1행, 0열, n+1열)는 항상 외부로 간주하여 BFS 시작점으로 처리
//        for (int i = 0; i <= n + 1; i++) {
//            for (int j = 0; j <= n + 1; j++) {
//                if (i == 0 || i == n + 1 || j == 0 || j == n + 1) {
//                    q.add(new int[]{i, j});
//                    check[i][j] = true;
//                } else
//                    check[i][j] = false;
//            }
//        }
//        // BFS를 이용하여 경계에서부터 침수될 수 있는 영역(높이가 mSeaLevel 미만인 영역)을 방문 처리
//        while (!q.isEmpty()) {
//            int[] front = q.poll();
//            for (int i = 0; i < 4; i++) {
//                int[] rear = {front[0] + dx[i], front[1] + dy[i]};
//                // 내부 영역 (패딩 제외): 인덱스 1 ~ n
//                if (rear[0] >= 1 && rear[0] <= n && rear[1] >= 1 && rear[1] <= n) {
//                    // 아직 방문하지 않았고, 해당 셀의 높이가 mSeaLevel 미만이면 침수 가능
//                    if (!check[rear[0]][rear[1]] && mMap[rear[0]][rear[1]] < mSeaLevel) {
//                        q.add(rear);
//                        check[rear[0]][rear[1]] = true;
//                    }
//                }
//            }
//        }
//        // BFS 이후, 방문하지 않은 셀은 침수되지 않은 건조 영역
//        int ret = 0;
//        for (int i = 1; i <= n; i++)
//            for (int j = 1; j <= n; j++)
//                if (!check[i][j])
//                    ret++;
//        return ret;
//    }
//
//    // maxArea: 구조물 mStructure를 한 개 설치하여 해수면 상승 시 침수되지 않는 최대 면적을 반환
//    public int maxArea(int M, int[] mStructure, int mSeaLevel) {
//        int ret = -1;
//        // M==1 인 경우, 한 칸씩 수정하며 최대 면적을 계산
//        if (M == 1) {
//            for (int i = 1; i <= n; i++) {
//                for (int j = 1; j <= n; j++) {
//                    // 해당 칸에 구조물 높이만큼 더해줌
//                    modifiedMap[i][j] = initMap[i][j] + mStructure[0];
//                    // 침수되지 않은 면적 계산 후 최댓값 갱신
//                    ret = Math.max(ret, unsubmergedArea(modifiedMap, mSeaLevel));
//                    // 원래 높이로 복원
//                    modifiedMap[i][j] = initMap[i][j];
//                }
//            }
//            return ret;
//        }
//
//        // 구조물의 연속 높이 차이를 해시값으로 계산
//        int hash = 0;
//        for (int i = 0; i + 1 < M; i++)
//            hash = hash * 10 + (mStructure[i] - mStructure[i + 1] + 5);
//
//        // 미리 저장해둔 후보 리스트에서 해당 해시값에 맞는 후보들을 순회
//        for (Candidate wall : candidate[hash]) {
//            if (wall.isHorizontal) {
//                // 가로 후보인 경우, 구조물의 첫 부분의 높이가 기준이 됨.
//                // 만약 역순이면, 후보 구간의 오른쪽 끝(즉, wall.c + M - 1)의 높이를 기준으로 함.
//                int height = mStructure[0] + (wall.isReverse ? initMap[wall.r][wall.c + M - 1] : initMap[wall.r][wall.c]);
//                // 후보 구간의 모든 칸을 height로 맞춤 (구조물 설치 후, 모든 칸의 최종 높이는 동일해야 함)
//                for (int i = 0; i < M; i++)
//                    modifiedMap[wall.r][wall.c + i] = height;
//                // 침수되지 않은 면적 계산
//                ret = Math.max(ret, unsubmergedArea(modifiedMap, mSeaLevel));
//                // 원래 높이로 복원
//                for (int i = 0; i < M; i++)
//                    modifiedMap[wall.r][wall.c + i] = initMap[wall.r][wall.c + i];
//            } else { // 세로 후보인 경우
//                int height = mStructure[0] + (wall.isReverse ? initMap[wall.r + M - 1][wall.c] : initMap[wall.r][wall.c]);
//                for (int i = 0; i < M; i++)
//                    modifiedMap[wall.r + i][wall.c] = height;
//                ret = Math.max(ret, unsubmergedArea(modifiedMap, mSeaLevel));
//                for (int i = 0; i < M; i++)
//                    modifiedMap[wall.r + i][wall.c] = initMap[wall.r + i][wall.c];
//            }
//        }
//        return ret;
//    }
//}
