import sys

input = sys.stdin.readline

N = int(input())
arr = [list(map(int, input().split())) for _ in range(N)]

# knapsack = [[0] * (N + 1) for _ in range(N + 1)]
# for i in range(1, N + 1):
#     for j in range(1, N + 1):
#         time = arr[i - 1][0]
#         profit = arr[i - 1][1]

#         if time > j:
#             knapsack[i][j] = knapsack[i - 1][j]
#         else:
#             knapsack[i][j] = max(knapsack[i - 1][j], knapsack[i - 1][j - time] + profit)

# print(knapsack[N][N])

# dp = [0] * (N + 1)
# for i in range(N):
#     time = arr[i][0]
#     profit = arr[i][1]

#     for j in range(N, time - 1, -1):
#         dp[j] = max(dp[j], dp[j - time] + profit)
# print(dp[N])


dp = [0] * (N + 1)

# dp[i] = maximum profit achievable starting from day i (0-based index)
for i in range(N - 1, -1, -1):
    time, profit = arr[i]
    end_day = i + time
    if end_day <= N:
        dp[i] = max(profit + dp[end_day], dp[i + 1])
    else:
        dp[i] = dp[i + 1]

print(dp[0])