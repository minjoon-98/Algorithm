import sys
input = sys.stdin.readline

N = int(input())
sequence = list(map(int, input().split()))

# dp[left][right]는 sequence[left:right+1] 구간이 회문이 되기 위해 필요한 최소 삽입 횟수
dp = [[0] * N for _ in range(N)]

for length in range(1, N):
    for left in range(N - length):
        right = left + length
        if sequence[left] == sequence[right]:
            dp[left][right] = dp[left + 1][right - 1]
        else:
            dp[left][right] = min(dp[left + 1][right], dp[left][right - 1]) + 1

print(dp[0][N - 1])