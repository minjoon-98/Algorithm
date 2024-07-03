import sys
from collections import deque
input = sys.stdin.readline

def bfs(v):
    queue = deque([v])
    distances = [0] * (N+1)
    visited = [False] * (N+1)
    visited[v] = True
    while queue:
        curr = queue.popleft()
        for n in graph[curr]:
            if not visited[n]:
                visited[n] = True
                distances[n] = distances[curr] + 1
                queue.append(n)
    
    return sum(distances)

N, M = map(int, input().split())
graph = [[] for _ in range(N+1)]

for _ in range(M):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

min_depth = float('inf')
person = 0

for i in range(1, N+1):
    depth = bfs(i)
    if depth < min_depth:
        min_depth = depth
        person = i

print(person)