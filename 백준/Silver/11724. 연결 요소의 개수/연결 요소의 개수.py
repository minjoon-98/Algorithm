import sys
sys.setrecursionlimit(10**6)    # 런타임 에러 오류 수정

N, M = map(int, sys.stdin.readline().split())
graph = [[0]*(N+1) for _ in range(N+1)]
count = 0

for _ in range(M):
    u, v = map(int, sys.stdin.readline().split())
    graph[u][v] = graph[v][u] = 1

visited = [False] * (N+1)

def dfs(v):
    visited[v] = True
    for i in range(1, N+1):
        if not visited[i] and graph[v][i] == 1:
            dfs(i)

for i in range(1, N+1):
    if visited [i] == False:
        dfs(i)
        count += 1

print(count)