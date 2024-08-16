import sys
input = sys.stdin.readline

N = int(input())

dp = [0] * (N+1)
dp[1] = 1
for i in range(2, N+1):
    min_count = i
    for j in range(1, int(i**(1/2))+1):
        min_count = min(min_count, dp[i - j**2] + 1)
        dp[i] = min_count
print(dp[N])