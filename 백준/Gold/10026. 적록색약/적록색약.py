from collections import deque
import sys
input = sys.stdin.readline

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def rgb_bfs(r, c):
    q = deque()
    q.append((r, c))
    color = map[r][c]
    while q:
        x, y = q.popleft()
        visited[x][y] = True
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < N and 0 <= ny < N and not visited[nx][ny] and map[nx][ny] == color:
                q.append((nx, ny))
                visited[nx][ny] = True
            
def rg_bfs(r, c):
    q = deque()
    q.append((r, c))
    if map[r][c] == 'R' or map[r][c] == 'G':
        while q:
            x, y = q.popleft()
            visited[x][y] = True
            for i in range(4):
                nx, ny = x + dx[i], y + dy[i]
                if 0 <= nx < N and 0 <= ny < N and not visited[nx][ny]:
                    if map[nx][ny] == 'R' or map[nx][ny] == 'G':
                        q.append((nx, ny))
                        visited[nx][ny] = True
    else:
        color = map[r][c]
        while q:
            x, y = q.popleft()
            visited[x][y] = True
            for i in range(4):
                nx, ny = x + dx[i], y + dy[i]
                if 0 <= nx < N and 0 <= ny < N and not visited[nx][ny] and map[nx][ny] == color:
                    q.append((nx, ny))
                    visited[nx][ny] = True


N = int(input())
map = [input().strip() for _ in range(N)]

visited = [[False]*N for _ in range(N)]
rgb_count = 0

for i in range(N):
    for j in range(N):
        if not visited[i][j]:
            rgb_bfs(i, j)
            rgb_count += 1

visited = [[False]*N for _ in range(N)]
rg_count = 0

for i in range(N):
    for j in range(N):
        if not visited[i][j]:
            rg_bfs(i, j)
            rg_count += 1

print(str(rgb_count) + ' ' + str(rg_count))