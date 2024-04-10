import sys
INF = float('inf')
N, M = map(int, sys.stdin.readline().split())

stone = [int(sys.stdin.readline()) for _ in range(M)]

dp = [[INF]*(int((2*N)**0.5)+2) for _ in range(N+1)]
dp[1][0] = 0

for i in range(2, N+1):
    if i in stone:
        continue
    for v in range(1, int((2*i)**0.5)+1):
        dp[i][v] = min(dp[i-v][v-1], dp[i-v][v], dp[i-v][v+1]) + 1
answer = min(dp[N])

if answer == INF:
    print(-1)
else:
    print(answer)