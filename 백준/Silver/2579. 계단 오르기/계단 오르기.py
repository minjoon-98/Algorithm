import sys

N = int(sys.stdin.readline())
stairs = [int(sys.stdin.readline()) for _ in range(N)]

dp = [0] * (N+1)
dp[1] = stairs[0]  # 첫 번째 계단은 첫 번째 계단 값만큼의 점수를 가집니다.
if N > 1:
    dp[2] = max(stairs[0] + stairs[1], stairs[1])  # 두 번째 계단은 첫 번째 계단 값과 두 번째 계단 값 중 큰 값을 선택합니다.

for i in range(3, N+1):
    # 현재 계단을 밟을 때의 최대 점수는 (i-2)번째 계단을 밟았을 때의 최대 점수와 (i-3)번째 계단을 밟았을 때의 최대 점수 중 큰 값에 현재 계단 값을 더한 값입니다.
    dp[i] = max(dp[i-2] + stairs[i-1], dp[i-3] + stairs[i-2] + stairs[i-1])

print(dp[N])