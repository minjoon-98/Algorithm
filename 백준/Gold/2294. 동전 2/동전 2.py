import sys

n, k = map(int, sys.stdin.readline().split())
coins = []

for _ in range(n):
    coins.append(int(sys.stdin.readline()))  # 각 동전의 가치를 정수로 변환하여 저장

dp = [[float('inf')] * (k + 1) for _ in range(n + 1)]

for i in range(n + 1):
    dp[i][0] = 0  # 0원을 만들기 위한 동전의 개수는 항상 0입니다.

for i in range(1, n + 1):
    for j in range(1, k + 1):
        if j < coins[i - 1]:  # 현재 동전의 가치보다 가치가 작을 때
            dp[i][j] = dp[i - 1][j]  # 이전 동전까지 사용한 경우를 그대로 가져옵니다.
        else:
            dp[i][j] = min(dp[i - 1][j], dp[i][j - coins[i - 1]] + 1)  # 이전 동전까지 사용한 경우와 현재 동전을 사용한 경우 중 더 작은 값을 선택합니다.

if dp[n][k] == float('inf'):
    print(-1)  # 동전으로 가치 k를 만들 수 없는 경우
else:
    print(dp[n][k])  # 동전으로 가치 k를 만들 수 있는 경우, 그 최소 동전의 개수를 출력합니다.
