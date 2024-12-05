import sys
input = sys.stdin.readline

N, M = map(int, input().split())
map = [list(map(int, input().split())) for _ in range(N)]
H = int(input())

dp = [[float('inf')] * M for _ in range(N)]
dp[0][0] = map[0][0]

for i in range(N):
    for j in range(M):
        if i + 1 < N:
            dp[i + 1][j] = min(dp[i + 1][j], dp[i][j] + map[i + 1][j])
        if j + 1 < M:
            dp[i][j + 1] = min(dp[i][j + 1], dp[i][j] + map[i][j + 1])

total_sneak = dp[N - 1][M - 1]

if total_sneak <= H:
    print('YES')
    print(total_sneak)
else:
    print('NO')



# # DP 왜 틀리는지 모르겠음
# import sys
# input = sys.stdin.readline

# N, M = map(int, input().split())
# map = [list(map(int, input().split())) for _ in range(N)]
# H = int(input())
# dp = [[0]*(M+1) for _ in range(N+1)]

# dp[0][0] = dp[0][1] = dp[1][0] = H

# for i in range(1, N+1):
#     for j in range(1, M+1):
#         dp[i][j] = max(dp[i-1][j], dp[i][j-1]) - map[i-1][j-1]

# if dp[N][M] >= 0:
#     print('YES')
#     print(H - dp[N][M])
# else:
#     print('NO')



# # BFS 시간초과
# from collections import deque
# import sys
# input = sys.stdin.readline

# N, M = map(int, input().split())
# map = [list(map(int, input().split())) for _ in range(N)]
# H = int(input())

# q = deque()
# q.append((0, 0, H - map[0][0]))

# remaining = 0

# while q:
#     x, y, h = q.popleft()
#     if x == N-1 and y == M-1:
#         remaining = max(remaining, h)
#     for dx, dy in ((0, 1), (1, 0)):
#         nx, ny = x + dx, y + dy
#         if 0 <= nx < N and 0 <= ny < M:
#             nh = h - map[nx][ny]
#             if 0 <= nh:
#                 q.append((nx, ny, nh))

# if remaining:
#     print('YES')
#     print(H - remaining)
# else:
#     print('NO')