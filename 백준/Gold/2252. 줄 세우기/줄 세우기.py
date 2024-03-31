from collections import deque
import sys

N, M = map(int, sys.stdin.readline().split())

graph = [[] for _ in range(N+1)]
in_degree = [0 for _ in range(N+1)]
queue = deque()
answer = []

for _ in range(M):
    a, b = map(int, sys.stdin.readline().split())
    graph[a].append(b)
    in_degree[b] += 1

for i in range(1, N+1):
    if in_degree[i] == 0:
        queue.append(i)

while queue:
    v = queue.popleft()
    answer.append(v)
    for i in graph[v]:
        in_degree[i] -= 1
        if in_degree[i] == 0:
            queue.append(i)

print(*answer)