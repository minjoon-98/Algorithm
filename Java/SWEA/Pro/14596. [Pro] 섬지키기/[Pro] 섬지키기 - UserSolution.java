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
