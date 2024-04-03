from collections import deque
import sys

N = int(sys.stdin.readline())
M = int(sys.stdin.readline())

graph = [[] for _ in range(N+1)]
in_degree = [0 for _ in range(N+1)]
needs = [[0]*(N+1) for _ in range(N+1)]
que = deque()

for _ in range(M):
    X, Y, K = map(int, sys.stdin.readline().split())
    graph[Y].append((X, K))
    in_degree[X] += 1

# 진입 차수가 0인 부품을 큐에 넣음
for i in range(1, N+1):
    if in_degree[i] == 0:
        que.append(i)

# 위상 정렬 시작
while que:
    curr = que.popleft()
    for next, required_amount in graph[curr]:
        # 만약 현 제품이 기본 부품이면 (needs 행렬에 0이 N+1개 배열됨)
        if needs[curr].count(0) == N + 1:
            needs[next][curr] += required_amount
        # 현 제품이 중간 부품이면
        for i in range(1, N+1):
            needs[next][i] += needs[curr][i] * required_amount
        in_degree[next] -= 1
        if in_degree[next] == 0:
            que.append(next)
for parts in enumerate(needs[N]):
    if parts[1] > 0:
        print(*parts)