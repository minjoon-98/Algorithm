import sys

N = int(sys.stdin.readline())
matrix = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]

dp = [[0]*N for _ in range(N)]

for mid in range(1, N):
    for start in range(N-mid):
        end = start + mid
        dp[start][end] = 2**31
        for i in range(start, end):
            dp[start][end] = min(dp[start][end], dp[start][i]+dp[i+1][end]+matrix[start][0]*matrix[i][1]*matrix[end][1])

print(dp[0][N-1])