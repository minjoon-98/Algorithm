from collections import deque
import sys

N = int(sys.stdin.readline())
M = int(sys.stdin.readline())

graph = [[] for _ in range(N+1)]
indegree = [0] * (N+1)

for _ in range(M):
    post, pre, weight = map(int, sys.stdin.readline().split())
    graph[pre].append((post, weight))
    indegree[post] += 1

dp = [[0]*(N+1) for _ in range(N+1)]
q = deque()

for i in range(1, N+1):
    if (indegree[i] == 0):
        dp[i][i] = 1
        q.append(i)

while q:
    pre = q.popleft()
    for post, weight in graph[pre]:
        for j in range(1, N+1):
            dp[post][j] += dp[pre][j] * weight
        indegree[post] -= 1
        if indegree[post] == 0:
            q.append(post)

for i in range(1, N):
    if dp[N][i]:
        print(i, dp[N][i])