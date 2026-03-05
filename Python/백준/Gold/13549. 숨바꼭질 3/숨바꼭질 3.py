import sys
from collections import deque

input = sys.stdin.readline

N, K = map(int, input().split())

### 오답 초보 풀이
# def dfs(time, position):
#     if position == K:
#         print(time)
#         exit(0)
#     if abs(K - position) > abs(K - position * 2):
#         dfs(time, position * 2)
#     elif position < K:
#         dfs(time + 1, position + 1)
#     elif position > K:
#         dfs(time + 1, position - 1)
        
answer = float('inf')

def bfs(time, position):
    global answer

    # Iterative 0-1 BFS using a deque to avoid recursion and track visited states.
    start = position
    max_limit = max(N, K) * 2 + 2

    visited = [False] * (max_limit + 1)
    dq = deque()

    visited[start] = True
    dq.append([time, start])

    while dq:
        time, x = dq.popleft()
        if x == K and time < answer:
            answer = time
            return

        # Move with cost 0: teleport to x * 2
        nx = x * 2
        if 0 <= nx <= max_limit and not visited[nx]:
            visited[nx] = True
            dq.appendleft([time, nx])
        # Moves with cost 1: x - 1 and x + 1
        for nx in (x - 1, x + 1):
            if 0 <= nx <= max_limit and not visited[nx]:
                visited[nx] = True
                dq.append([time + 1, nx])

bfs(0, N)

print(answer)