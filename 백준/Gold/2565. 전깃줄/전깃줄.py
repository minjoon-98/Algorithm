import sys
input = sys.stdin.readline

N = int(input())

lines = sorted([tuple(map(int, input().split())) for _ in range(N)])
B_positions = [b for a, b in lines]

dp = [1] * N

# Longest Increasing Subsequence 길이 계산 (DP)
for i in range(N):
    for j in range(i):
        if B_positions[j] < B_positions[i]:
            dp[i] = max(dp[i], dp[j] + 1)
lines_to_save = max(dp)

print(N - lines_to_save)