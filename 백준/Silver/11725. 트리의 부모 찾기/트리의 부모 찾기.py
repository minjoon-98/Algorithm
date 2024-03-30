from collections import deque
import sys

N = int(sys.stdin.readline())

graph = [[] for _ in range(N+1)]
parents = [0] * (N+1)  # 부모 노드를 저장할 배열 초기화

for _ in range(N-1):
    u, v = map(int, sys.stdin.readline().split())
    graph[u].append(v)
    graph[v].append(u)

visited = [False] * (N+1)

def bfs(start):
    queue = deque([start])  # 시작 노드부터 탐색 시작
    visited[start] = True
    while queue:
        v = queue.popleft()
        for i in graph[v]:
            if not visited[i]:
                queue.append(i)
                visited[i] = True
                parents[i] = v  # 현재 노드의 직전에 방문한 노드를 부모로 저장

bfs(1)

for i in range(2, N+1):  # 노드 1을 제외한 나머지 노드의 부모 출력
    print(parents[i])