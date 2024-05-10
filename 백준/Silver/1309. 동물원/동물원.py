import sys
input = sys.stdin.readline

N = int(input())
dp = [0] * (N+1)
dp[0] = 1
dp[1] = 3

for i in range(2, N+1):
    # dp[i] = dp[i-1] + 2*(dp[i-2] + (dp[i-1] - dp[i-2])/2)
    # dp[i-1]: 새로운 우리에 사자가 안들어간 경우
    # 2*(dp[i-2] + (dp[i-1] - dp[i-2])//2) : 새로운 우리에 왼쪽에 들어왔을 경우 + 새로운 우리에 오른쪽에 들어왔을 경우
    dp[i] = (2*dp[i-1] + dp[i-2])%9901
print(dp[N]%9901)