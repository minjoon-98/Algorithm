from collections import deque
import sys
input = sys.stdin.readline

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def fire_bfs():
    q = deque()
    for r, c in fires:
        q.append((r, c))
        fire_time[r][c] = 0
    while q:
        x, y = q.popleft()
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < h and 0 <= ny < w and building[nx][ny] == '.' and fire_time[nx][ny] == -1:
                fire_time[nx][ny] = fire_time[x][y] + 1
                q.append((nx, ny))

def man_bfs():
    q = deque()
    for r, c in man:
        q.append((r, c, 0))
        visited[r][c] = True
    while q:
        x, y, t = q.popleft()
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if not (0 <= nx < h) or not (0 <= ny < w):
                return t + 1
            if 0 <= nx < h and 0 <= ny < w and building[nx][ny] == '.' and not visited[nx][ny]:
                if fire_time[nx][ny] == -1 or t + 1 < fire_time[nx][ny]:
                    visited[nx][ny] = True
                    q.append((nx, ny, t + 1))
    return 'IMPOSSIBLE'

N = int(input())

for _ in range(N):
    w, h = map(int, input().split())
    building = [list(input().strip()) for _ in range(h)]
    fires = []
    man = []
    for i in range(h):
        for j in range(w):
            if building[i][j] == '*':
                fires.append((i, j))
            elif building[i][j] == '@':
                man.append((i, j))
    
    fire_time = [[-1]*w for _ in range(h)]
    visited = [[False]*w for _ in range(h)]
    fire_bfs()
    result = man_bfs()
    print(result)