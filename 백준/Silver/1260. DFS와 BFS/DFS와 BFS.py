from collections import deque
import sys

N, M, V = map(int, sys.stdin.readline().split())

# 그래프의 인접 리스트 생성
graph = [[] for _ in range(N + 1)]
for _ in range(M):
    a, b = map(int, sys.stdin.readline().split())
    graph[a].append(b)
    graph[b].append(a)

# DFS 및 BFS에 사용할 방문 여부 리스트
visitedDFS = [False] * (N + 1)
visitedBFS = [False] * (N + 1)

def dfs(v):
    visitedDFS[v] = True
    print(v, end=' ')
    for i in graph[v]:
        if not visitedDFS[i]:
            dfs(i)

def bfs(v):
    queue = deque([v])
    visitedBFS[v] = True
    while queue:
        v = queue.popleft()
        print(v, end=' ')
        for i in graph[v]:
            if not visitedBFS[i]:
                queue.append(i)
                visitedBFS[i] = True

# DFS 및 BFS 시작 전에 방문 여부 초기화
for i in range(1, N + 1):
    graph[i].sort()

dfs(V)
print()
bfs(V)