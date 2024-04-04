import sys

N, M = map(int, sys.stdin.readline().split())
graph = []
for _ in range(N):
    graph.append(list(sys.stdin.readline().strip()))
count = 0

def dfs(x, y):
    if graph[x][y] == '|':
        graph[x][y] = 1 # 방문체크
        for dx in [-1, 1]:
            nx = x + dx
            if 0 <= nx < N and graph[nx][y] == '|': # 범위 내에 있는 상하 체크
                dfs(nx, y)
    if graph[x][y] == '-':
        graph[x][y] = 1 # 방문체크
        for dy in [-1, 1]:
            ny = y + dy
            if 0 <= ny < M and graph[x][ny] == '-': # 범위 내에 있는 좌우 체크
                dfs(x, ny)

for i in range(N):
    for j in range(M):
        if graph[i][j] == '|' or graph[i][j] == '-':
            dfs(i, j)
            count += 1
print(count)