import sys
input = sys.stdin.readline

N = int(input())
boxs = list(map(int, input().split()))
boxs.insert(0, 0)

dp = [0]*(N+1)

for i in range(1, N+1):
    for j in range(i):
        if boxs[j] < boxs[i]:
            dp[i] = max(dp[i], dp[j] + 1)

print(max(dp))