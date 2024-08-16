import sys
input = sys.stdin.readline

N = int(input())

dp = [i for i in range(N + 1)]

for i in range(2, N+1):
    for j in range(int(i**(1/2)), 0, -1):
        if dp[i] > dp[i - j**2] + 1:
            dp[i] = dp[i - j**2] + 1

print(dp[N])