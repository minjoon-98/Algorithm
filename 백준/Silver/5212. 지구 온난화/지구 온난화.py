import sys
input = sys.stdin.readline

# 상하좌우로 인접한 4방향 탐색을 위한 방향 배열
dx = [-1, 1, 0, 0]  # 상, 하
dy = [0, 0, -1, 1]  # 좌, 우

# row, column 입력 받기
R, C = map(int, input().split())

# 지도 입력 받기
grid = [list(input().strip()) for _ in range(R)]

# 새로 변형된 지도를 저장할 리스트
new_grid = [['.' for _ in range(C)] for _ in range(R)]

# 각 셀에 대해 주변 셀을 검사
for i in range(R):
    for j in range(C):
        if grid[i][j] == 'X':  # 땅인 경우만 처리
            sea_count = 0
            # 주변 4방향을 탐색하여 바다가 몇 개인지 계산
            for d in range(4):
                ni, nj = i + dx[d], j + dy[d]
                if 0 <= ni < R and 0 <= nj < C:
                    if grid[ni][nj] == '.':
                        sea_count += 1
                else:
                    sea_count += 1  # 지도 밖은 바다로 간주

            # 바다가 3개 이상이면, 해당 땅은 바다로 변함
            if sea_count >= 3:
                new_grid[i][j] = '.'
            else:
                new_grid[i][j] = 'X'
        else:
            new_grid[i][j] = '.'  # 원래 바다는 그대로 바다

# 새 지도에서 섬이 있는 부분만 출력
min_row, max_row = R, -1
min_col, max_col = C, -1

# 새 지도의 경계를 찾기
for i in range(R):
    for j in range(C):
        if new_grid[i][j] == 'X':  # 땅이 있는 곳만
            min_row = min(min_row, i)
            max_row = max(max_row, i)
            min_col = min(min_col, j)
            max_col = max(max_col, j)

# 결과 출력
for i in range(min_row, max_row + 1):
    print(''.join(new_grid[i][min_col:max_col + 1]))