import sys
input = sys.stdin.readline

N = int(input())
M = int(input())

VIP = [int(input()) for _ in range(M)]

dp = [1] * (N+1)
for i in range(2, N+1):
    dp[i] = dp[i-2] + dp[i-1]

count = 0
answer = 1

for i in range(1, N+1):
    if i in VIP:
        answer *= dp[count]
        count = 0
    else:
        count += 1
answer *= dp[count]

print(answer)