from collections import deque
import sys

N, M, V = map(int, sys.stdin.readline().split())
graph = [[0]*(N+1) for _ in range(N+1)]

for _ in range(M):
    x, y = map(int, sys.stdin.readline().split())
    graph[x][y] = graph[y][x] = 1

visitedDFS = [False] * (N+1)
visitedBFS = [False] * (N+1)

def dfs(v):
    visitedDFS[v] = True
    print(v, end=' ')
    for i in range(N+1):
        if not visitedDFS[i] and graph[v][i] == 1:
            dfs(i)

def bfs(v):
    q = deque([v])
    visitedBFS[v] = True
    while q:
        v = q.popleft()
        print(v, end=' ')
        for i in range(N+1):
            if not visitedBFS[i] and graph[v][i] == 1:
                q.append(i)
                visitedBFS[i] = True

dfs(V)
print()
bfs(V)