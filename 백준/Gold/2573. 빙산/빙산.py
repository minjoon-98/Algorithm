import sys

# 입력 받기
N, M = map(int, input().split())
iceberg = []
for _ in range(N):
    iceberg.append(list(map(int, sys.stdin.readline().split())))

ice = {}  # 얼음 좌표만 모은 dict - 키: tuple(행, 열), 값: 1(얼음 있음) or 0(얼음 없음)
for i in range(1, N-1):  # 첫 번째 행과 열, 마지막 행과 열에는 얼음이 없으니 1부터 마지막 전까지 반복
    for j in range(1, M-1):
        if iceberg[i][j]:
            ice[(i, j)] = 1  # 얼음이 있는 부분 ice에 추가

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]


def DFS(x, y):
    q = [(x, y)]  # 현재 얼음에 연결된 얼음 좌표들을 추가할 stack

    while q:  # 연결된 얼음이 있는 동안 반복
        r, c = q.pop()
        if not visited[r][c]:
            visited[r][c] = True

            count = 0  # 상하좌우 중에 바다인 곳 개수

            for i in range(4):
                row = r + dy[i]
                col = c + dx[i]

                # 얼음이었다가 물이 된 부분을 재탐색 하는 것 방지 (얼음이었다가 이번 해에 물이 된 부분은 True로 바뀌어있음)
                if not visited[row][col]:
                    if iceberg[row][col] <= 0:  # 바다이면
                        count += 1
                    else:  # 빙산이면
                        q.append((row, col))

            iceberg[r][c] -= count

            if iceberg[r][c] <= 0:  # 높이가 0보다 같거나 작아지면 빙산 녹음 처리
                ice[(r, c)] = 0


year = 0
while sum(ice.values()) > 0:  # 얼음이 전부 녹을때까지 반복
    dfs_count = 1  # DSF 탐색 횟수 == 빙산 덩어리 수
    visited = [[False]*M for _ in range(N)]

    for key, value in ice.items():
        if value and not visited[key[0]][key[1]]:
            if dfs_count == 2:  # 빙산 덩어리가 2가 되면 for문 종료
                print(year)
                break
            DFS(key[0], key[1])
            dfs_count += 1
    else:  # 빙산 덩어리가 2가 되지 않았으면 while문 반복
        year += 1
        continue
    break  # 빙산 덩어리가 2가 되었으면 while문 종료

else:
    print(0)