import sys

def dfs(start, now, cost, depth):
    global ans
    if depth == N:
        if graph[now][start]:
            cost += graph[now][start]
            if ans > cost:
                ans = cost
        return

    if cost > ans:
        return

    for i in range(N):
        if not visited[i] and graph[now][i]:
            visited[i] = True
            dfs(start, i, cost + graph[now][i], depth + 1)
            visited[i] = False

N = int(input())
graph = [list(map(int, sys.stdin.readline().split()))for _ in range(N)]
ans = sys.maxsize
visited = [0] * N
for i in range(N):
    visited[i] = True
    dfs(i, i, 0, 1)
    visited[i] = False
print(ans)