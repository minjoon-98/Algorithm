from collections import deque
import sys

N = int(sys.stdin.readline())
M = int(sys.stdin.readline())

graph = [[0] for _ in range(N)]

for i in range(M):
    u, v = map(int, sys.stdin.readline().split())
    graph[u-1].append(v-1)
    graph[v-1].append(u-1)

visited = [False] * (N)
count = 0

def bfs(start): 
    global count
    queue = deque([start])
    while queue:
        v = queue.popleft()
        visited[v] = True
        for i in graph[v]:
            if not visited[i]:
                count += 1
                queue.append(i)
                visited[i] = True

bfs(0)
print(count)