import sys
input = sys.stdin.readline

N = int(input())
dp = [0, 1, 2]

for i in range(3, N+1):
    if i%2 == 0:
        dp.append(dp[i-1] + dp[i//2])
    else:
        dp.append(dp[i-1])

print(dp[N]%1000000000)