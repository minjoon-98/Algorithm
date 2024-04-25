import sys
from collections import deque

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def bfs(i, j):
    q = deque()
    q.append([i, j])
    visited[i][j] = 1
    while q:
        x, y = q.popleft()
        visited[x][y] = True
        for d in range(4):
            nx, ny = x + dx[d], y + dy[d]
            if 0 <= nx < N and 0 <= ny < M:
                if iceberg[nx][ny] == 0:
                    visited[x][y] += 1
                if not visited[nx][ny] and iceberg[nx][ny] != 0:
                    q.append([nx, ny])
                    visited[nx][ny] = True

N, M = map(int, sys.stdin.readline().split())
iceberg = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]

year = 0
while True:
    count = 0
    visited = [[False] * M for _ in range(N)]
    for i in range(N):
        for j in range(M):
            if not visited[i][j] and iceberg[i][j] != 0:
                bfs(i, j)
                count += 1
    for i in range(N):
        for j in range(M):
            if visited[i][j]:
                iceberg[i][j] -= (visited[i][j] - 1)
                if iceberg[i][j] < 0:
                    iceberg[i][j] = 0

    year += 1
    
    if count == 0:
        print(0)
        exit()
    elif count >= 2:
        break

print(year - 1)