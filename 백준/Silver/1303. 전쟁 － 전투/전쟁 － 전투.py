from collections import deque
import sys
input = sys.stdin.readline

N, M = map(int, input().split())
soldier = [list(map(str, input().strip())) for _ in range(M)]
visited = [[False]*N for _ in range(M)]

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def bfs(r, c):
    count = 0
    q = deque()
    q.append([r, c])
    color = soldier[r][c]
    while q:
        count += 1
        x, y = q.popleft()
        visited[x][y] = True
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < M and 0 <= ny < N and not visited[nx][ny] and soldier[nx][ny] == color:
                q.append([nx, ny])
                visited[nx][ny] = True
    return count

powerB, powerW = 0, 0
for i in range(M):
    for j in range(N):
        if visited[i][j]:
            continue
        if soldier[i][j] == "W":
            powerW += pow(bfs(i, j), 2)
        else:
            powerB += pow(bfs(i, j), 2)

print(str(powerW) + ' ' + str(powerB))