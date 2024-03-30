import sys
sys.setrecursionlimit(10**6)

N, M = map(int, sys.stdin.readline().split())   # 1 <= N <= 1000
graph = [[0]*(N+1) for _ in range(N+1)]  # 수정된 부분
count = 0

for _ in range(M):
    u, v = map(int, sys.stdin.readline().split())
    graph[u][v] = graph[v][u] = 1   # 여기서 graph[0]은 존재하지 않음

visited = [False] * (N+1)  # 수정된 부분 따라서 N+1개 만듦

def dfs(v):
    visited[v] = True
    for i in range(1, N+1):  # 수정된 부분 
        if not visited[i] and graph[v][i] == 1:  # 수정된 부분
            dfs(i)

for j in range(1, N+1):  # 수정된 부분
    if visited[j] == False:
        dfs(j)
        count += 1

print(count)