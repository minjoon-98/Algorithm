import sys

N, M = map(int, sys.stdin.readline().split())
graph1 = [[] for _ in range(N+1)]
graph2 = [[] for _ in range(N+1)]
for _ in range(M):
    a, b = map(int, sys.stdin.readline().split())
    graph1[a].append(b)
    graph2[b].append(a)

def dfs(graph, v):
    stack = [v]
    visited = set()
    count = 0
    while stack:
        u = stack.pop()
        if u not in visited:
            visited.add(u)
            stack += graph[u]
            count += 1
    return count

answer = 0

for i in range(1, N+1):
    if dfs(graph1, i) > (N+1)/2:
        answer += 1
    if dfs(graph2, i) > (N+1)/2:
        answer += 1

print(answer)